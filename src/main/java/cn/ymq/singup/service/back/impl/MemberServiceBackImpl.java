package cn.ymq.singup.service.back.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.ymq.singup.dao.IActionDAO;
import cn.ymq.singup.dao.IMemberDAO;
import cn.ymq.singup.dao.IRoleDAO;
import cn.ymq.singup.service.back.IMemberServiceBack;
import cn.ymq.singup.service.back.abs.AbstractServiceBack;
import cn.ymq.vo.Member;

@Service
public class MemberServiceBackImpl extends AbstractServiceBack implements IMemberServiceBack {
	@Resource
	private IMemberDAO memberDAO;
	@Resource
	private IRoleDAO roleDAO;
	@Resource
	private IActionDAO actionDAO;

	@Override
	public Member get(String mid) {
		return this.memberDAO.findById(mid);
	}

	@Override
	public Map<String, Object> listAuthByMember(String mid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allRoles", this.roleDAO.findAllRoleFlag(mid));
		map.put("allActions", this.actionDAO.findAllActionFlag(mid));
		return map;
	}

	@Override
	@RequiresUser
	public boolean editPassword(String mid, String oldPassword, String newPassword) {
		Member vo = this.memberDAO.findById(mid); // 根据已有的用户编号取得用户的完整信息
		if (vo == null) { // 该用户已经没有了
			return false;
		}
		if (oldPassword.equals(vo.getPassword())) { // 判断旧密码是否相等
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("mid", mid);
			params.put("newPassword", newPassword);
			return this.memberDAO.doUpdatePassword(params);
		}
		return false;
	}

	@RequestMapping("member")
	@RequiresPermissions("member:list")
	@Override
	public List<Member> list() {
		return this.memberDAO.findAll();
	}

	@RequiresRoles("member")
	@RequiresPermissions("member:edit")
	@Override
	public boolean editPasswordByAdmin(String mid, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mid", mid);
		map.put("password", password);
		return this.memberDAO.doUpdatePasswordByAdmin(map);
	}

	@Override
	public boolean editLocked(String mid, int locked) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mid", mid);
		map.put("locked", locked);
		return this.memberDAO.doUpdateLocked(map);
	}

	@Override
	@RequiresRoles("member")
	@RequiresPermissions("member:add")
	public Map<String, Object> addPre() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allRoles", this.roleDAO.findAll());
		return map;
	}

	@Override
	@RequiresRoles("member")
	@RequiresPermissions("member:add")
	public boolean add(Member vo, Set<Integer> rids) {
		if (this.memberDAO.findById(vo.getMid()) == null) {
			vo.setSflag(0);
			vo.setRegdate(new Date());
			vo.setLocked(0);
			if (this.memberDAO.doCreate(vo)) {
				Iterator<Integer> iter = rids.iterator();
				while (iter.hasNext()) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("mid", vo.getMid());
					map.put("rid", iter.next());
					this.memberDAO.doCreateMemberAndRole(map);
				}
				return true;
			}
		}
		return false;
	}

	@RequiresRoles("member")
	@RequiresPermissions("member:edit")
	@Override
	public Map<String, Object> editPre(String mid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allRoles", this.roleDAO.findAll());
		map.put("member", this.memberDAO.findById(mid));
		map.put("memberRoles", this.memberDAO.findAllRoleByMember(mid));
		return map;
	}

	@RequiresRoles("member")
	@RequiresPermissions("member:edit")
	@Override
	public boolean edit(Member vo, Set<Integer> rids) {
		if (this.memberDAO.findById(vo.getMid()) != null) {
			if (this.memberDAO.doUpdate(vo)) {
				if (this.memberDAO.doRemoveMemberAndRole(vo.getMid())) {
					Iterator<Integer> iter = rids.iterator();
					while (iter.hasNext()) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("mid", vo.getMid());
						map.put("rid", iter.next());
						this.memberDAO.doCreateMemberAndRole(map);
					}
					return true;
				}
			}
		}
		return false;
	}

}
