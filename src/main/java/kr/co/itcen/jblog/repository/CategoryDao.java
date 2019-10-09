package kr.co.itcen.jblog.repository;

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
}
