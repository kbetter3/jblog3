package kr.co.itcen.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{userId:(?!assets).*}/admin")
public class AdminController {

	@GetMapping({"", "/basic"})
	public String basicView() {
		return "blog/blog-admin-basic";
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
