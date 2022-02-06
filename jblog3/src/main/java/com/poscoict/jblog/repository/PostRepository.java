package com.poscoict.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;
@Repository
public class PostRepository {
	@Autowired
	private SqlSession sqlSession; 
	
	
	public boolean insertPost(PostVo postVo) {
		return sqlSession.insert("post.insertPost", postVo) == 1;
	}
	
	public List<PostVo> viewPost() {
		return sqlSession.selectList("post.viewPost");
	}
	
	public CategoryVo findCategory(String name, String id) {
		
		Map<String, String> map = new HashMap<>();
		map.put("name", name);
		map.put("id", id);
		
		return sqlSession.selectOne("category.findCategory", map);
	}
	
	// 한개의 글 불러오기 아직 미완
}
