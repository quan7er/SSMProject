package cn.ymq.singup.service.back;

import java.util.Map;

import cn.ymq.vo.Bespeak;

public interface IBespeakServiceBack {
	
	public Map<String,Object> listByStatus(String column,String keyWord,Integer currentPage,Integer lineSize,Integer status);

	public Bespeak get(int beid);
	
	public boolean editNote(int beid,String newNote);
	
	public boolean editStatus(int beid,int status);

	public Map<String,Object> loadCount(); 
}
