package kr.co.itcen.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession session;
	
	public int insertUser(UserVo userVo) {
		return session.insert("user.insert", userVo);
	}
	
	public int idDuplicationCheck(UserVo userVo) {
		return session.selectOne("user.idDuplicationCheck", userVo);
	}
}
