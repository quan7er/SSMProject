package cn.ymq.singup.dao;

import java.util.List;
import java.util.Map;

import cn.ymq.vo.Bespeak;
import cn.ymq.vo.BespeakCount;

public interface IBespeakDAO {
	
	public boolean doCreate(Bespeak vo);
	
	public List<Bespeak> findAllSplitByStatus(Map<String,Object> params);

	public Integer getAllCountByStatus(Map<String,Object> params);

	public Bespeak findById(Integer beid);
	
	public boolean doUpdateNote(Map<String,Object> params);
	
	public boolean doUpdateStatus(Map<String,Object> params);

	public List<BespeakCount> getStatusCount();
}
