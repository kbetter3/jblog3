package kr.co.itcen.jblog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.itcen.jblog.result.ApiResult;
import kr.co.itcen.jblog.security.MyAuth;
import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.type.ResponseCode;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.UserVo;

@MyAuth
@RestController
@RequestMapping("/api/{userId:(?!assets).*}/admin")
public class AdminApiController {

	@Autowired
	private CategoryService categoryService;
	
	// category 추가
	@PostMapping("/category")
	public ApiResult<CategoryVo> createCategory(@PathVariable String userId, CategoryVo categoryVo, Errors errors, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		if (authUser.getId().equals(userId) == false) {
			return new ApiResult<>(ResponseCode.FORBIDDEN);
		}
		
		// category validation
		categoryVo.createValidCheck(errors);
		
		if (errors.hasErrors()) {
			ResponseCode responseCode = ResponseCode.findByCode(errors.getAllErrors().get(0).getCode());
			
			if (responseCode == null) {
				responseCode = ResponseCode.UNKNOWN_ERROR;
			}
			
			return new ApiResult<>(responseCode);
		}
		
		
		// categoryVo에 blogId 세팅
		categoryVo.setBlogId(authUser.getId());
		
		// service 호출 후 결과에 따라 return
		return categoryService.createCategory(categoryVo);
	}
	
	@DeleteMapping("/category")
	public ApiResult<CategoryVo> deleteCategory(@PathVariable String userId, CategoryVo categoryVo, Errors errors, HttpSession session) {
		// 본인 확인
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		if (authUser.getId().equals(userId) == false) {
			return new ApiResult<>(ResponseCode.FORBIDDEN);
		}
		
		// 유효성 검사
		categoryVo.deleteValidCheck(errors);
		
		if (errors.hasErrors()) {
			ResponseCode responseCode = ResponseCode.findByCode(errors.getAllErrors().get(0).getCode());
			
			if (responseCode == null) {
				responseCode = ResponseCode.UNKNOWN_ERROR;
			}
			
			return new ApiResult<>(responseCode);
		}
		
		// blogId 세팅
		categoryVo.setBlogId(authUser.getId());
		
		// service 호출
		return categoryService.deleteCategory(categoryVo);
	}
}
