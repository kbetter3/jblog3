package kr.co.itcen.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import kr.co.itcen.jblog.repository.UserDao;
import kr.co.itcen.jblog.result.ApiResult;
import kr.co.itcen.jblog.type.ResponseCode;
import kr.co.itcen.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * 유저 회원가입 및 블로그 생성
	 */
	@Transactional(rollbackFor = Exception.class)
	public ApiResult<Object> join(UserVo userVo) {
		// 유저 가입시 blog도 생성
		if (userDao.insertUser(userVo) == 1 && blogService.createBlog(userVo).isStatus()) {
			return new ApiResult<>();
		} else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new ApiResult<>(ResponseCode.DB_ERROR);
		}
	}
	
	
	/**
	 * 아이디 중복체크
	 */
	public ApiResult<Object> idDuplicationCheck(UserVo userVo) {
		if (userDao.idDuplicationCheck(userVo) == 0) {
			return new ApiResult<>();
		} else {
			return new ApiResult<>(ResponseCode.DUPLICATED_USER);
		}
	}
	
	
	public ApiResult<UserVo> login(UserVo userVo) {
		UserVo authUser = userDao.selectUserByIdAndPasswd(userVo);
		
		if (authUser != null) {
			return new ApiResult<>(authUser);
		} else {
			return new ApiResult<>(ResponseCode.LOGIN_ERROR);
		}
	}
}
