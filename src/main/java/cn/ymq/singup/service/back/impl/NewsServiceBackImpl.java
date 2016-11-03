package cn.ymq.singup.service.back.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Service;

import cn.ymq.singup.dao.IDictionaryDAO;
import cn.ymq.singup.dao.INewsDAO;
import cn.ymq.singup.service.back.INewsServiceBack;
import cn.ymq.singup.service.back.abs.AbstractServiceBack;
import cn.ymq.vo.News;
@Service
public class NewsServiceBackImpl extends AbstractServiceBack implements INewsServiceBack {
	@Resource
	private IDictionaryDAO dictionaryDAO;
	@Resource
	private INewsDAO newsDAO;
	
	@Override
	@RequiresRoles("news")
	@RequiresPermissions("news:add")
	public boolean add(News vo) {
		vo.setPubdate(new Date());
		if(this.newsDAO.findByTitle(vo.getTitle()) == null){
			return this.newsDAO.doCreate(vo);
		}
		return false;
	}
	@Override
	@RequiresRoles("news")
	@RequiresPermissions("news:add")
	public Map<String, Object> addPre() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("allNewsType", this.dictionaryDAO.findAllByItem("news"));
		return map;
	}

	@Override
	public News getByTitle(String title) {
		return this.newsDAO.findByTitle(title);
	}
	
	@Override
	public Map<String, Object> list(String column, String keyWord, int currentPage, int lineSize) {
		Map<String,Object> params = super.HandleParams(column, keyWord, currentPage, lineSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("allNews", this.newsDAO.findAllSplit(params));
		result.put("newsCount", this.newsDAO.getAllCount(params));
		return result;
	}
	@Override
	public Map<String, Object> listNone(String column, String keyWord, int currentPage, int lineSize) {
		Map<String,Object> params = super.HandleParams(column, keyWord, currentPage, lineSize);
		params.put("flag", 0);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("allNews", this.newsDAO.findAllSplitByFlag(params));
		result.put("newsCount", this.newsDAO.getAllCountByFlag(params));
		return result;
	}
	@Override
	public Map<String, Object> editPre(int nid) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("allNewsType", this.dictionaryDAO.findAllByItem("news"));
		map.put("news", this.newsDAO.findById(nid));
		return map;
	}
	@Override
	public boolean edit(News vo) {
		News oldNews = this.newsDAO.findByTitle(vo.getTitle());
		if(oldNews != null){
			if(!vo.getNid().equals(oldNews.getNid())){
				return false;
			}
		}
		return this.newsDAO.doUpdate(vo);
	}
	@Override
	public boolean remove(Set<Integer> nids) {
		if(nids == null || nids.size() == 0){
			return false;
		}
		return this.newsDAO.doRemove(nids.toArray());
	}
	@Override
	public List<News> listByFlag(int currentPage, int lineSize, int flag) {
		Map<String,Object> param = super.HandleParams(null, null, currentPage, lineSize);
		param.put("flag", flag);
		return this.newsDAO.findAllSplitByFlag(param);
	}

}
