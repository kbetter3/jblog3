package kr.co.itcen.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession session;

	// category 생성
	public int insertCategory(CategoryVo categoryVo) {
		return session.insert("category.insert", categoryVo);
	}
	
	// category 삭제
	public int deleteCategory(CategoryVo categoryVo) {
		return session.update("category.delete", categoryVo);
	}
	
	// category 조회
	public List<CategoryVo> selectByBlogId(CategoryVo categoryVo) {
		return session.selectList("category.selectByBlogId", categoryVo);
	}
	
	// category 개수 조회
	public int selectCntByBlogId(CategoryVo categoryVo) {
		return session.selectOne("category.selectCntByBlogId", categoryVo);
	}
	
	// category 존재 여부 확인 no, blog_id
	public int existCheck(CategoryVo categoryVo) {
		return session.selectOne("category.existCheck", categoryVo);
	}
	
	// 해당 블로그의 category별 게시글수를 포함한 카테고리 정보 조회
	public List<CategoryVo> selectWithPostCntByBlogIdAndCategoryNo(CategoryVo categoryVo) {
		return session.selectList("category.selectWithPostCntByBlogIdAndCategoryNo", categoryVo);
	}
}
