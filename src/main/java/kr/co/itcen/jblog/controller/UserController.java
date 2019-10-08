package kr.co.itcen.jblog.controller;

import javax.servlet.http.HttpSession;

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
	
	// 회원가입
	@PostMapping("/join")
	public String join(UserVo userVo, Errors errors) {
		// validation check
		log.debug(userVo);
		userVo.joinValidCheck(errors);
		
		// error 발생시 view로 forward
		if (errors.hasErrors()) {
			return "user/join";
		}
		
		// insert
		ApiResult<UserVo> apiResult = userService.join(userVo);
		
		// TODO: insert 실패시 -> 삭제 고려해야함
		if (!apiResult.isStatus()) {
			return "user/join";
		}
		
		return "user/joinsuccess";
	}
	
	@GetMapping("/login")
	public String loginView(@ModelAttribute UserVo userVo) {
		return "user/login";
	}
	
	// 로그인
	@PostMapping("/login")
	public String login(UserVo userVo, Errors errors, HttpSession session) {
		// TODO: 로그인 validation
		userVo.loginValidCheck(errors);
		
		if (errors.hasErrors()) {
			return "user/login";
		}
		
		ApiResult<UserVo> apiResult = userService.login(userVo);
		
		if (!apiResult.isStatus()) {
			errors.rejectValue("id", apiResult.getMessageCode(), apiResult.getMessage());
			return "user/login";
		}
		// session에 authUser 추가
		session.setAttribute("authUser", apiResult.getData());
		// TODO: spring security 사용 검토
	
		// TODO: 로그인한 사용자 블로그로 이동할 수 있도록 처리해야함
		return "redirect:/" + apiResult.getData().getId();
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
}
