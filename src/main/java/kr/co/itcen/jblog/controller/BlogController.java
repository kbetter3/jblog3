package kr.co.itcen.jblog.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{userId:(?!assets).*}")
public class BlogController {

	@RequestMapping({"", "/{userId}", "/{userId}/{categoryId}"})
	public String test(@PathVariable Optional<String> userId, @PathVariable Optional<Integer> categoryId) {
		return null;
	}
}
