package kr.co.itcen.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
	@Transactional
	public ApiResult<CategoryVo> deleteCategory(CategoryVo categoryVo) {
		if (categoryDao.deleteCategory(categoryVo) == 1) {
			// 카테고리 개수가 1개 미만일 경우 에러 발생
			if (categoryDao.selectCntByBlogId(categoryVo) < 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return new ApiResult<>(ResponseCode.CATEGORY_CAN_NOT_BE_LESS_THAN_ONE);
			}
			return new ApiResult<>();
		} else {
			return new ApiResult<>(ResponseCode.DB_ERROR);
		}
	}
	
	/**
	 * 카테고리 조회
	 */
	public ApiResult<CategoryVo> selectCategoryByBlogId(CategoryVo categoryVo) {
		// TODO: 쿼리 수정 - 해당 블로그의 카테고리별 포스트 수를 조회하는 쿼리 작성해야함
		List<CategoryVo> list = categoryDao.selectByBlogId(categoryVo);
		
		return new ApiResult<>(list);
	}
	
	
	/**
	 * 카테고리 접근 가능 여부조회(본인의 블로그의 카테고리)
	 */
	public ApiResult<CategoryVo> existCheck(CategoryVo categoryVo) {
		if (categoryDao.existCheck(categoryVo) == 1) {
			return new ApiResult<>();
		} else {
			return new ApiResult<>(ResponseCode.FORBIDDEN);
		}
	}
}
