package kr.co.itcen.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	public String joinView() {
		return "user/join";
	}
	
	public String loginView() {
		return "user/login";
	}
	
	public String login() {
		return null;
	}
}
