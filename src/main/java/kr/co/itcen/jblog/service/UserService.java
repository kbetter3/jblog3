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
	
	@Transactional(rollbackFor = Exception.class)
	public ApiResult<Object> joinUser(UserVo userVo) {
		if (userDao.insertUser(userVo) == 1 && blogService.createBlog(userVo).isStatus()) {
			return new ApiResult<>();
		} else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new ApiResult<>(ResponseCode.DB_ERROR);
		}
	}
	
	public ApiResult<Object> idDuplicationCheck(UserVo userVo) {
//	public ApiResult<Object> idDuplicationCheck(String userId) {
//		UserVo userVo = new UserVo();
//		userVo.setId(userId);
		
		if (userDao.idDuplicationCheck(userVo) == 0) {
			return new ApiResult<>();
		} else {
			return new ApiResult<>(ResponseCode.DUPLICATED_USER);
		}
	}
}
