package cn.ymq.singup.service.back.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Service;

import cn.ymq.singup.dao.IBespeakDAO;
import cn.ymq.singup.service.back.IBespeakServiceBack;
import cn.ymq.singup.service.back.abs.AbstractServiceBack;
import cn.ymq.vo.Bespeak;
import cn.ymq.vo.BespeakCount;

@Service
public class BespeakServiceBackImpl extends AbstractServiceBack implements IBespeakServiceBack {
	@Resource
	private IBespeakDAO bespeakDAO;

	@Override
	@RequiresRoles("bespeak")
	@RequiresPermissions("bespeak:list")
	public Map<String, Object> listByStatus(String column, String keyWord, Integer currentPage, Integer lineSize,
			Integer status) {
		Map<String, Object> params = super.HandleParams(column, keyWord, currentPage, lineSize);
		params.put("status", status);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("allBespeaks", this.bespeakDAO.findAllSplitByStatus(params));
		result.put("bespeakCount", this.bespeakDAO.getAllCountByStatus(params));
		return result;
	}

	@Override
	@RequiresRoles("bespeak")
	@RequiresPermissions("bespeak:list")
	public Bespeak get(int beid) {
		return this.bespeakDAO.findById(beid);
	}

	@Override
	@RequiresRoles("bespeak")
	@RequiresPermissions("bespeak:edit")
	public boolean editNote(int beid, String newNote) {
		Map<String, Object> params = new HashMap<String, Object>();
		Bespeak vo = this.bespeakDAO.findById(beid);
		params.put("beid", beid);
		params.put("newNote", vo.getNote() + newNote + "\n");
		if(vo.getStatus().equals(0)){
			params.put("status", 1);
			this.bespeakDAO.doUpdateStatus(params);
		}
		return this.bespeakDAO.doUpdateNote(params);
	}

	@Override
	@RequiresRoles("bespeak")
	@RequiresPermissions("bespeak:edit")
	public boolean editStatus(int beid, int status) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("beid", beid);
		params.put("status", status);
		return this.bespeakDAO.doUpdateStatus(params);
	}

	@Override
	public Map<String, Object> loadCount() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("all", this.bespeakDAO.getAllCountByStatus(map));
		Iterator<BespeakCount> iter = this.bespeakDAO.getStatusCount().iterator();
		while(iter.hasNext()){
			BespeakCount bc = iter.next();
			map.put("status"+bc.getStatus(),bc.getCount());
		}
		return map;
	}
}
