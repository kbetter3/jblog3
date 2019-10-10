package kr.co.itcen.jblog.controller;

import javax.servlet.http.HttpSession;

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
import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.UserVo;

@MyAuth
@Controller
@RequestMapping("/{userId:(?!assets).*}/admin")
public class AdminController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
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
		// 본인여부 검사
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
		
		// apiResult의 status에 따라 return 분기 처리
		if (!apiResult.isStatus()) {
			errors.rejectValue("title", apiResult.getCode(), apiResult.getMessage());
			return "blog/blog-admin-basic";
		}
		
		return "redirect:/" + blogVo.getId() + "/admin";
	}
	
	@GetMapping("/write")
	public String writeView() {
		return "blog/blog-admin-write";
	}
	
	@GetMapping("/category")
	public String categoryView(@PathVariable String userId, HttpSession session, Model model) {
		// 본인확인
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		if (authUser.getId().equals(userId) == false) {
			return "redirect:/" + userId;
		}
		
		// category 조회 및 model 추가
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setBlogId(authUser.getId());
		
		ApiResult<CategoryVo> apiResult = categoryService.selectCategoryByBlogId(categoryVo);
		model.addAttribute("categoryList", apiResult.getDatas());
		
		return "blog/blog-admin-category";
	}
}
