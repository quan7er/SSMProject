package cn.ymq.singup.service.back.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ymq.singup.dao.IDictionaryDAO;
import cn.ymq.singup.service.back.IDictionaryServiceBack;

@Service
public class DictionaryServiceBackImpl implements IDictionaryServiceBack {
	@Resource
	private IDictionaryDAO dictionaryDAO;
	
	@Override
	public Map<String, Object> listBespeak() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("allEdus", this.dictionaryDAO.findAllByItem("edu"));
		map.put("allBetypes", this.dictionaryDAO.findAllByItem("betype"));
		map.put("allSources", this.dictionaryDAO.findAllByItem("source"));
		return map;
	}

}
