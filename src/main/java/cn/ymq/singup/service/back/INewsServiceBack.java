package cn.ymq.singup.service.back;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.ymq.vo.News;

public interface INewsServiceBack {

	public boolean add(News vo);

	public Map<String, Object> addPre();

	public News getByTitle(String title);
	
	public Map<String,Object> list(String column, String keyWord, int currentPage, int lineSize);
	
	public Map<String,Object> listNone(String column, String keyWord, int currentPage, int lineSize);

	public Map<String,Object> editPre(int nid);
	
	public boolean edit(News vo);
	
	public boolean remove(Set<Integer> nids);
	
	public List<News> listByFlag(int currentPage,int lineSize,int flag);

}
