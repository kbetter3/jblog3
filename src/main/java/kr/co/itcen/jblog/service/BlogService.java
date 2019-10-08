package kr.co.itcen.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.BlogDao;
import kr.co.itcen.jblog.result.ApiResult;
import kr.co.itcen.jblog.type.ResponseCode;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.UserVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	
	public ApiResult<Object> createBlog(UserVo userVo) {
		BlogVo blogVo = new BlogVo();
		blogVo.setId(userVo.getId());
		blogVo.setTitle(userVo.getId() + "님의 블로그");
		
		if (blogDao.insertBlog(blogVo) == 1) {
			return new ApiResult<>();
		} else {
			return new ApiResult<>(ResponseCode.DB_ERROR);
		}
	}
	
}
