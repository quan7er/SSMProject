package cn.ymq.action.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.ymq.util.action.AbstractAction;

@Controller
public class ShiroMemberAction extends AbstractAction {

	@RequestMapping("/admin/logoutUrl")
	@RequiresUser
	public ModelAndView logoutUrl() {
		ModelAndView mav = new ModelAndView(super.getValue("forward.back.page"));
		super.setMsgAndUrl(mav, "shiro.logout.msg", "front.index.action");
		super.logout();
		return mav;
	}

	@RequestMapping("/admin/successUrl")
	public ModelAndView seccessUrl() {
		return new ModelAndView(super.getValue("shiro.successUrl.page"));
	}

	@RequestMapping("/loginUrl")
	public ModelAndView loginUrl() {
		String url = super.getValue("shiro.successUrl.page");
		if (SecurityUtils.getSubject().getPrincipal() == null) {
			url = super.getValue("shiro.loginUrl.page");
		}
		return new ModelAndView(url);

	}

	@RequestMapping("/unauthUrl")
	public ModelAndView unauthUrl() {
		return new ModelAndView(super.getValue("shiro.unauthUrl.page"));
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