package com.poscoict.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.exception.UserRepositoryException;
import com.poscoict.jblog.vo.UserVo;

@Repository
public class UserRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(UserVo vo) {
		int count = sqlSession.insert("user.insert",vo);
		return count == 1;
	}
	
	public UserVo findById(String id) {
		return sqlSession.selectOne("user.findByNo", id);
	}
	
	public UserVo findByIdAndPassword(String id, String password) throws UserRepositoryException{

		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("p", password);
		
		UserVo vo = sqlSession.selectOne("user.findByIdAndPassword", map);

		return vo;
	}

}
