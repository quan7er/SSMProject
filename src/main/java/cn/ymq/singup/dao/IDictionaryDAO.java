package cn.ymq.singup.dao;

import java.util.List;

import cn.ymq.vo.Dictionary;

public interface IDictionaryDAO {
	
	public List<Dictionary> findAllByItem(String item);
	
	public Dictionary findById(int dtid);
	
}
