package cn.ymq.singup.dao;

import java.util.List;
import java.util.Map;

import cn.ymq.vo.News;

public interface INewsDAO {
	
	public News findByTitle(String title);
	
	public boolean doCreate(News vo);
	
	public List<News> findAllSplit(Map<String,Object> params);
	
	public Integer getAllCount(Map<String,Object> params);
	
	public List<News> findAllSplitByFlag(Map<String,Object> params);
	
	public Integer getAllCountByFlag(Map<String,Object> params);
	
	public News findById(Integer nid);
	
	public boolean doUpdate(News vo);
	
	public boolean doRemove(Object ids[]);

}
