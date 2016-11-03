package cn.ymq.singup.dao;

import java.util.List;
import java.util.Set;

import cn.ymq.vo.Role;

public interface IRoleDAO {
	
	public List<Role> findAll();
	
	public Set<String> findAllRoleFlag(String mid);
}
