package cn.ymq.singup.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.ymq.vo.Member;

public interface IMemberDAO {
	
	public Member findById(String mid);
	
	public boolean doUpdatePassword(Map<String,Object> params) ;
	
	public List<Member> findAll();
	
	public boolean doUpdatePasswordByAdmin(Map<String,Object> params);

	public boolean doUpdateLocked(Map<String,Object> params);

	public boolean doCreate(Member vo);
	
	public boolean doCreateMemberAndRole(Map<String,Object> params);
	
	public boolean doUpdate(Member vo);
	
	public Set<Integer> findAllRoleByMember(String mid);
	
	public boolean doRemoveMemberAndRole(String mid);
	
}
