package cn.ymq.singup.action.back;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.ymq.singup.service.back.IBespeakServiceBack;
import cn.ymq.util.action.AbstractAction;
import cn.ymq.util.split.SplitPageUtil;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/bespeak/*")
public class BespeakActionBack extends AbstractAction {
	@Resource
	private IBespeakServiceBack bespeakServiceBack;

	@RequestMapping("count")
	@RequiresUser
	public ModelAndView count(HttpServletResponse response){
		JSONObject obj = new JSONObject();
		obj.put("stat", this.bespeakServiceBack.loadCount());
		super.print(response, obj);
		return null;
	}
	
	
	@RequestMapping("list")
	@RequiresRoles("bespeak")
	@RequiresPermissions("bespeak:list")
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(super.getValue("back.bespeak.list.page"));
		SplitPageUtil spu = new SplitPageUtil(request, "name");
		Map<String, Object> result = this.bespeakServiceBack.listByStatus(spu.getColumn(), spu.getKeyWord(),
				spu.getCurrentPage(), spu.getLineSize(), null);
		super.handleSplit(mav, result.get("bespeakCount"), "真实姓名:name|联系电话:phone|学历:edu|毕业院校:school|报名类型:type|信息来源:src",
				"back.bespeak.list.action", spu);
		mav.addObject("type", "all");
		mav.addObject("allBespeaks", result.get("allBespeaks"));
		return mav;
	}
	
	@RequestMapping("wait")
	@RequiresRoles("bespeak")
	@RequiresPermissions("bespeak:list")
	public ModelAndView wait(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(super.getValue("back.bespeak.list.page"));
		SplitPageUtil spu = new SplitPageUtil(request, "name");
		Map<String, Object> result = this.bespeakServiceBack.listByStatus(spu.getColumn(), spu.getKeyWord(),
				spu.getCurrentPage(), spu.getLineSize(), 0);
		super.handleSplit(mav, result.get("bespeakCount"), "真实姓名:name|联系电话:phone|学历:edu|毕业院校:school|报名类型:type|信息来源:src",
				"back.bespeak.list.action", spu);
		mav.addObject("type", "wait");
		mav.addObject("allBespeaks", result.get("allBespeaks"));
		return mav;
	}
	
	@RequestMapping("finish")
	@RequiresRoles("bespeak")
	@RequiresPermissions("bespeak:list")
	public ModelAndView finish(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(super.getValue("back.bespeak.list.page"));
		SplitPageUtil spu = new SplitPageUtil(request, "name");
		Map<String, Object> result = this.bespeakServiceBack.listByStatus(spu.getColumn(), spu.getKeyWord(),
				spu.getCurrentPage(), spu.getLineSize(), 1);
		super.handleSplit(mav, result.get("bespeakCount"), "真实姓名:name|联系电话:phone|学历:edu|毕业院校:school|报名类型:type|信息来源:src",
				"back.bespeak.list.action", spu);
		mav.addObject("type", "finish");
		mav.addObject("allBespeaks", result.get("allBespeaks"));
		return mav;
	}
	
	@RequestMapping("invalid")
	@RequiresRoles("bespeak")
	@RequiresPermissions("bespeak:list")
	public ModelAndView invalid(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(super.getValue("back.bespeak.list.page"));
		SplitPageUtil spu = new SplitPageUtil(request, "name");
		Map<String, Object> result = this.bespeakServiceBack.listByStatus(spu.getColumn(), spu.getKeyWord(),
				spu.getCurrentPage(), spu.getLineSize(), 2);
		super.handleSplit(mav, result.get("bespeakCount"), "真实姓名:name|联系电话:phone|学历:edu|毕业院校:school|报名类型:type|信息来源:src",
				"back.bespeak.list.action", spu);
		mav.addObject("type", "invalid");
		mav.addObject("allBespeaks", result.get("allBespeaks"));
		return mav;
	}

	@RequestMapping("show")
	@RequiresRoles("bespeak")
	@RequiresPermissions("bespeak:list")
	public ModelAndView show(int beid, HttpServletResponse response) {
		JSONObject obj = new JSONObject();
		obj.put("bespeak", this.bespeakServiceBack.get(beid));
		super.print(response, obj);
		return null;
	}

	@RequestMapping("editNote")
	@RequiresRoles("bespeak")
	@RequiresPermissions("bespeak:edit")
	public ModelAndView editNote(Integer beid, String note, HttpServletResponse response) {
		String newNote = "【" + super.getMid() + " - " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
				+ "】" + note;
		if (this.bespeakServiceBack.editNote(beid, newNote)) {
			super.print(response, true);
		} else {
			super.print(response, false);
		}
		return null;
	}
	
	@RequestMapping("editStatus")
	@RequiresRoles("bespeak")
	@RequiresPermissions("bespeak:edit")
	public ModelAndView editStatus(Integer beid, Integer status, HttpServletResponse response) {
		if (this.bespeakServiceBack.editStatus(beid, status)) {
			super.print(response, true);
		} else {
			super.print(response, false);
		}
		return null;
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
