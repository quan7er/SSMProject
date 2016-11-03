package cn.ymq.singup.service.front.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ymq.singup.dao.IBespeakDAO;
import cn.ymq.singup.dao.IDictionaryDAO;
import cn.ymq.singup.service.front.IBespeakServiceFront;
import cn.ymq.vo.Bespeak;
@Service
public class BespeakServiceFrontImpl implements IBespeakServiceFront {
	@Resource
	IDictionaryDAO dictionaryDAO;
	@Resource
	IBespeakDAO bespeakDAO;
	@Override
	public boolean add(Bespeak vo) {
		vo.setEdu(this.dictionaryDAO.findById(vo.getEduid()).getTitle());
		vo.setType(this.dictionaryDAO.findById(vo.getTypeid()).getTitle());
		vo.setSrc(this.dictionaryDAO.findById(vo.getSrcid()).getTitle());
		vo.setIndate(new Date());
		vo.setStatus(0);
		vo.setNote("");
		return this.bespeakDAO.doCreate(vo);
	}

}
