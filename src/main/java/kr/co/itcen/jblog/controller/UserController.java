package kr.co.itcen.jblog.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.itcen.jblog.result.ApiResult;
import kr.co.itcen.jblog.service.UserService;
import kr.co.itcen.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/join")
	public String joinView(@ModelAttribute UserVo userVo) {
		return "user/join";
	}
	
	// TODO: 회원가입
	@PostMapping("/join")
	public String join(UserVo userVo, Errors errors) {
		// validation check
		log.debug(userVo);
		userVo.joinValidCheck(errors);
		
		// error 발생시 view로 forward
		if (errors.hasErrors()) {
			return "user/join";
		}
		
		// TODO: insert
		// user insert시 category도 같이 insert해야함
		ApiResult apiResult = userService.joinUser(userVo);
		
		// TODO: insert 실패시 -> 삭제 고려해야함
		if (!apiResult.isStatus()) {
			return "user/join";
		}
		
		return "redirect:/user/login";
	}
	
	@GetMapping("/login")
	public String loginView() {
		return "user/login";
	}
	
	@PostMapping("/login")
	public String login() {
		return null;
	}
}
