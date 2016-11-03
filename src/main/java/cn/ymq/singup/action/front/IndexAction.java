package cn.ymq.singup.action.front;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.ymq.util.action.AbstractAction;
@Controller
public class IndexAction extends AbstractAction {
	
	@RequestMapping("/errorsUrl")
	public ModelAndView errors() {
		return new ModelAndView(super.getValue("validation.error.page"));
	}
	@RequestMapping("/index")
	public ModelAndView index(){
		return new ModelAndView(super.getValue("front.index.page"));
	}
	@RequestMapping("/admin/indexBack")
	@RequiresUser  
	public ModelAndView indexBack() {
		return new ModelAndView(super.getValue("shiro.successUrl.page"));
	}
	@Override
	public String getFileUploadDir() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
