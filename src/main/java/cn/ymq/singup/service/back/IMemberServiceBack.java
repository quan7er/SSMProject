package cn.ymq.singup.service.back;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.ymq.vo.Member;

public interface IMemberServiceBack {
	
	public Member get(String mid);

	public Map<String,Object> listAuthByMember(String mid);
	
	public boolean editPassword(String mid,String oldPassword,String newPassword) ;

	public List<Member> list() ;
	
	public boolean editPasswordByAdmin(String mid,String password);

	public boolean editLocked(String mid,int locked);
	
	public Map<String,Object> addPre();
	
	public boolean add(Member vo,Set<Integer> rids);
	
	public Map<String,Object> editPre(String mid);
	
	public boolean edit(Member vo,Set<Integer> rids);

}
