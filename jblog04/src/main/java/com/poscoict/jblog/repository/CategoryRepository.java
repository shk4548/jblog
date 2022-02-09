package com.poscoict.jblog.repository;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession; 
	
	public List<CategoryVo> viewCategory(String id) {
		System.out.println(id);
		return sqlSession.selectList("category.viewCategory", id);
	}
	
	public boolean updateCategory(CategoryVo categoryVo) {
		return sqlSession.update("category.updateCategory", categoryVo) == 1;
	}
	public boolean insertCateogry(CategoryVo categoryVo) {
		int count = sqlSession.insert("category.insertCateogry",categoryVo);
		return count == 1;
	}
	public boolean defaultCategory(String id) {
		int count = sqlSession.insert("category.defaultCategory", id);
		return count == 1;
	}
	
	public boolean deleteCategory (Long category_no) {
		System.out.println("RepositoryNo : " + category_no);
		int count = sqlSession.delete("category.deleteCategory", category_no) ;
		System.out.println("레파지토리 성공");
		return count == 1;
	}

	public Long countPost (Long category_no) {
		Long count = sqlSession.selectOne("post.countPost",category_no);
		return count;
	}
}
