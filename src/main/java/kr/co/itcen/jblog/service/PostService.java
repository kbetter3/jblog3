package kr.co.itcen.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.PostDao;
import kr.co.itcen.jblog.result.ApiResult;
import kr.co.itcen.jblog.type.ResponseCode;
import kr.co.itcen.jblog.vo.PostVo;

@Service
public class PostService {

	@Autowired
	private PostDao postDao;
	
	/**
	 * 게시글 등록
	 */
	public ApiResult<PostVo> createPost(PostVo postVo) {
		if (postDao.insertPost(postVo) == 1) {
			return new ApiResult<>();
		} else {
			return new ApiResult<>(ResponseCode.DB_ERROR);
		}
	}
	
}
