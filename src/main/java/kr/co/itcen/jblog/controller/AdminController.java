package kr.co.itcen.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.itcen.jblog.security.MyAuth;
import kr.co.itcen.jblog.vo.BlogVo;

@MyAuth
@Controller
@RequestMapping("/{userId:(?!assets).*}/admin")
public class AdminController {

	@GetMapping({"", "/basic"})
	public String basicView(@ModelAttribute BlogVo blogVo) {
		return "blog/blog-admin-basic";
	}
	
	@PostMapping({"", "/basic"})
	public String basic(BlogVo blogVo, Errors errors) {
		return null;
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
