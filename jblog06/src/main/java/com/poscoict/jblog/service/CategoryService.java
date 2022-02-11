package com.poscoict.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.CategoryRepository;
import com.poscoict.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<CategoryVo> getCategoryList(String id){
		System.out.println(id);
		return categoryRepository.viewCategory(id);
	}

	public boolean addCategory(CategoryVo categoryVo) {
		return categoryRepository.insertCateogry(categoryVo);
	}
	
	public Long getCountPost(Long category_no) {
		return categoryRepository.countPost(category_no);
	}

	public Boolean deleteCategory(Long category_no) {
		boolean result = categoryRepository.deleteCategory(category_no);
		return result;
		
	}


}
