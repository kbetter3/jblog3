package kr.co.itcen.jblog.repository;

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
}
