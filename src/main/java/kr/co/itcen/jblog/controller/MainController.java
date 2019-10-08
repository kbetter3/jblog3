package kr.co.itcen.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping({"", "/"})
	public String mainView() {
		return "main/index";
	}
}
