package kr.co.itcen.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.result.ApiResult;
import kr.co.itcen.jblog.type.ResponseCode;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.UserVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	
	/**
	 * 카테고리 생성(회원가입시 userVo의 userId를 이용하여 생성 userId == blogId)
	 */
	public ApiResult<CategoryVo> createCategory(UserVo userVo) {
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setTitle("기본카테고리");
		categoryVo.setDescription("기본카테고리");
		categoryVo.setBlogId(userVo.getId());
		
		return createCategory(categoryVo);
	}
	
	/**
	 * 카테고리 생성
	 */
	public ApiResult<CategoryVo> createCategory(CategoryVo categoryVo) {
		if (categoryDao.insertCategory(categoryVo) == 1) {
			return new ApiResult<>();
		} else {
			return new ApiResult<>(ResponseCode.DB_ERROR);
		}
	}
	
	/**
	 * 카테고리 삭제
	 */
	public ApiResult<CategoryVo> deleteCategory(CategoryVo categoryVo) {
		if (categoryDao.deleteCategory(categoryVo) == 1) {
			return new ApiResult<>();	
		} else {
			return new ApiResult<>(ResponseCode.DB_ERROR);
		}
	}
}
