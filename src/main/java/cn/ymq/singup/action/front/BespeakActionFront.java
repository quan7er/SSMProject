package cn.ymq.singup.action.front;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.ymq.singup.service.front.IBespeakServiceFront;
import cn.ymq.util.action.AbstractAction;
import cn.ymq.vo.Bespeak;

@Controller
@RequestMapping("/bespeak/*")
public class BespeakActionFront extends AbstractAction{
	@Resource
	IBespeakServiceFront bespeakServiceFront;

	@RequestMapping("addPre")
	public ModelAndView addPre(){
		ModelAndView mav = new ModelAndView(super.getValue("front.bespeak.add.page"));
		return mav;											
	}
	@RequestMapping("add")
	public ModelAndView add(Bespeak vo){
		ModelAndView mav = new ModelAndView(super.getValue("forward.front.page"));
		if(this.bespeakServiceFront.add(vo)){
			super.setMsgAndUrl(mav, "bespeak.add.success.msg", "front.index.action");
		} else {
			super.setMsgAndUrl(mav, "bespeak.add.failure.msg", "front.index.action");
		}
		return mav;
	}
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFileUploadDir() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
