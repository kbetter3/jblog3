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
	
	private final String defaultLogo = "/jblog3/assets/images/logo/default_logo.png";
	
	/**
	 * 회원가입시 블로그 생성
	 */
	public ApiResult<BlogVo> createBlog(UserVo userVo) {
		BlogVo blogVo = new BlogVo();
		blogVo.setId(userVo.getId());
		blogVo.setLogo(defaultLogo);
		blogVo.setTitle(userVo.getId() + "님의 블로그");
		
		if (blogDao.insertBlog(blogVo) == 1) {
			return new ApiResult<>();
		} else {
			return new ApiResult<>(ResponseCode.DB_ERROR);
		}
	}
	
	/**
	 * 블로그 수정
	 */
	public ApiResult<BlogVo> updateBlog(BlogVo blogVo) {
		if (blogDao.updateBlog(blogVo) == 1) {
			return new ApiResult<>();
		} else {
			return new ApiResult<>(ResponseCode.DB_ERROR);
		}
	}
	
	/**
	 * 블로그 조회
	 */
	public ApiResult<BlogVo> selectBlogById(BlogVo blogVo) {
		BlogVo vo = blogDao.selectBlogById(blogVo);
		
		if (vo == null) {
			return new ApiResult<>(ResponseCode.BLOG_NOT_FOUND);
		} else {
			return new ApiResult<>(vo);
		}
	}
}
