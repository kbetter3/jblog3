package kr.co.itcen.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.itcen.jblog.result.ApiResult;
import kr.co.itcen.jblog.service.UserService;
import kr.co.itcen.jblog.vo.UserVo;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

	@Autowired
	private UserService userService;
	
	
	@PostMapping("/idcheck")
	public ApiResult<Object> idDuplicationCheck(UserVo userVo) {
//		return userService.idDuplicationCheck(userId);
		return userService.idDuplicationCheck(userVo);
	}
}
