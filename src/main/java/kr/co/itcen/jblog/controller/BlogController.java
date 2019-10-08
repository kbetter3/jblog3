package kr.co.itcen.jblog.controller;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{userId:(?!assets).*}")
public class BlogController {

	private Log log = LogFactory.getLog(this.getClass());
	
	@GetMapping({"", "/{categoryId}", "/{categoryId}/{postId}"})
	public String blogView(@PathVariable String userId, @PathVariable Optional<Integer> categoryId, @PathVariable Optional<Integer> postId) {
		// TODO: 사용자 id만 있을 경우
		// TODO: categoryId까지 있을 경우
		// TODO: postId까지 있을 경우
		if (categoryId.isPresent()) {
			log.info("present");
		}
		
		return "blog/blog-main";
	}
}
