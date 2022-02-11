package com.poscoict.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.BlogVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public boolean updateBlog(BlogVo blogVo) {
		return sqlSession.update("blog.updateBlog", blogVo) ==1 ;
	}
	
	public BlogVo viewBlog(String id) {
		return sqlSession.selectOne("blog.viewBlog", id);
	}
	public boolean defaultBlog(String id) {
		int count = sqlSession.insert("blog.defaultBlog", id);
		return count == 1;
	}
}
