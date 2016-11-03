package cn.ymq.singup.action.back;

import java.io.File;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.ymq.singup.service.back.INewsServiceBack;
import cn.ymq.util.CreateStaticUtil;
import cn.ymq.util.action.AbstractAction;
import cn.ymq.util.split.SplitPageUtil;
import cn.ymq.vo.News;

@Controller
@RequestMapping("/admin/news/*")
public class NewsActionBack extends AbstractAction {
	@Resource
	private INewsServiceBack newsServiceBack;

	@RequestMapping("list")
	@RequiresRoles("news")
	@RequiresPermissions("news:list")
	public ModelAndView list(HttpServletRequest request) {
		SplitPageUtil spu = new SplitPageUtil(request, "title");
		ModelAndView mav = new ModelAndView(super.getValue("back.news.list.page"));
		Map<String, Object> result = this.newsServiceBack.list(spu.getColumn(), spu.getKeyWord(), spu.getCurrentPage(),
				spu.getLineSize());
		super.handleSplit(mav, result.get("newsCount"), "公告标题:title|公告摘要:abs", "back.news.list.action", spu);
		mav.addObject("allNews", result.get("allNews"));
		return mav;
	}

	@RequestMapping("listNone")
	@RequiresRoles("news")
	@RequiresPermissions("news:list")
	public ModelAndView listNone(HttpServletRequest request) {
		SplitPageUtil spu = new SplitPageUtil(request, "title");
		ModelAndView mav = new ModelAndView(super.getValue("back.news.list.page"));
		Map<String, Object> result = this.newsServiceBack.listNone(spu.getColumn(), spu.getKeyWord(),
				spu.getCurrentPage(), spu.getLineSize());
		super.handleSplit(mav, result.get("newsCount"), "公告标题:title|公告摘要:abs", "back.news.list.action", spu);
		mav.addObject("allNews", result.get("allNews"));
		return mav;
	}

	@RequestMapping("addPre")
	@RequiresRoles("news")
	@RequiresPermissions("news:add")
	public ModelAndView addPre() {
		ModelAndView mav = new ModelAndView(super.getValue("back.news.add.page"));
		mav.addAllObjects(this.newsServiceBack.addPre());
		return mav;
	}

	@RequestMapping("add")
	@RequiresRoles("news")
	@RequiresPermissions("news:add")
	public ModelAndView add(News vo, MultipartFile pic, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(super.getValue("forward.back.page"));
		if (pic != null) {
			String fileName = super.createFileName(pic);
			vo.setMid(super.getMid());
			vo.setPhoto(fileName);
			if (this.newsServiceBack.add(vo)) {
				super.saveFile(pic, fileName, request);
				super.setMsgAndUrl(mav, "vo.add.success.msg", "back.news.add.action");
			} else {
				super.setMsgAndUrl(mav, "vo.add.failure.msg", "back.news.add.action");
			}
		}
		return mav;
	}

	@RequestMapping("editPre")
	public ModelAndView editPre(Integer nid) {
		ModelAndView mav = new ModelAndView(super.getValue("back.news.edit.page"));
		mav.addAllObjects(this.newsServiceBack.editPre(nid));
		return mav;
	}

	@RequestMapping("edit")
	public ModelAndView edit(News vo, MultipartFile pic, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(super.getValue("forward.back.page"));
		if (pic != null) {
			if (pic.getSize() > 0) {
				if (!"nophoto.gif".equals(vo.getPhoto())) {
					vo.setPhoto(super.createFileName(pic));
				}
			}
			vo.setMid(super.getMid());
		}
		if (this.newsServiceBack.edit(vo)) {
			if (pic != null && pic.getSize() > 0) {
				super.saveFile(pic, vo.getPhoto(), request);
			}
			super.setMsgAndUrl(mav, "vo.edit.success.msg", "back.news.list.action");
		} else {
			super.setMsgAndUrl(mav, "vo.edit.failure.msg", "back.news.list.action");
		}
		return mav;
	}

	@RequestMapping("remove")
	public ModelAndView remove(HttpServletRequest request, HttpServletResponse response) {
		super.print(response, this.newsServiceBack.remove(super.getBacthIds(request)));
		return null;
	}

	@RequestMapping("checkTitle")
	@RequiresRoles("news")
	@RequiresPermissions("news:add")
	public ModelAndView checkTitle(String title, HttpServletResponse response) {
		super.print(response, this.newsServiceBack.getByTitle(title) == null);
		return null;
	}

	@RequestMapping("create")
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
		String filePath = request.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "news.static";
		new CreateStaticUtil<News>().create(new File(filePath), this.newsServiceBack.listByFlag(1, 13, 1));
		super.print(response, true);
		return null;
	}

	@Override
	public String getType() {
		return "公告";
	}

	@Override
	public String getFileUploadDir() {
		return "/upload/news/";
	}

}
