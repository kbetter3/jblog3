package kr.co.itcen.jblog.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.itcen.jblog.result.ApiResult;
import kr.co.itcen.jblog.security.MyAuth;
import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.UserVo;

@MyAuth
@Controller
@RequestMapping("/{userId:(?!assets).*}/admin")
public class AdminController {
	
	@Autowired
	private BlogService blogService;
	
	@GetMapping({"", "/basic"})
	public String basicView(@PathVariable String userId, HttpSession session, Model model) {
		// 본인여부 검사 
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		if (authUser.getId().equals(userId) == false) {
			return "redirect:/" + userId;
		}
		
		BlogVo blogVo = new BlogVo();
		blogVo.setId(userId);
		blogVo = blogService.selectBlogById(blogVo).getData();
		
		model.addAttribute("blogVo", blogVo);
		
		return "blog/blog-admin-basic";
	}
	
	@PostMapping({"", "/basic"})
	public String basic(@PathVariable String userId, BlogVo blogVo, Errors errors, HttpSession session) {
		// TODO: 본인여부 검사
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		if (authUser.getId().equals(userId) == false) {
			return "redirect:/" + userId;
		}
		
		blogVo.setId(authUser.getId());
		
		// 유효성 검사
		blogVo.updateValidCheck(errors);
		
		if (errors.hasErrors()) {
			return "blog/blog-admin-basic";
		}
		
		ApiResult<BlogVo> apiResult = blogService.updateBlog(blogVo);
		
		return "redirect:/" + blogVo.getId() + "/admin";
	}
	
	@GetMapping("/write")
	public String writeView() {
		return "blog/blog-admin-write";
	}
	
	@GetMapping("/category")
	public String categoryView() {
		return "blog/blog-admin-category";
	}
}
