package kr.co.itcen.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession session;
	
	public int insertBlog(BlogVo blogVo) {
		return session.insert("blog.insert", blogVo);
	}
	
	public int updateBlog(BlogVo blogVo) {
		return session.update("blog.update", blogVo);
	}
	
	public BlogVo selectBlogById(BlogVo blogVo) {
		return session.selectOne("blog.selectBlogById", blogVo);
	}
}
