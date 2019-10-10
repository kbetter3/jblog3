package kr.co.itcen.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	private SqlSession session;
	
	// 게시글 등록
	public int insertPost(PostVo postVo) {
		return session.insert("post.insert", postVo);
	}
	
	// 게시글 리스트 조회(blog_id, category_no)
	public List<PostVo> selectListByCategoryNo(PostVo postVo) {
		return session.selectList("post.selectListByCategoryNo", postVo);
	}
	
	// 게시글 단일 조회(no, category_no, blog_id)
	public PostVo selectByNoAndCategoryNo(PostVo postVo) {
		return session.selectOne("post.selectByNoAndCategoryNo", postVo);
	}
}
