package com.poscoict.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.PostRepository;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;

@Service
public class PostService {

	@Autowired 
	private PostRepository postRepository;	
	
	public CategoryVo getCategory(String name, String id) {
		return postRepository.findCategory(name, id);
	}
	
	public boolean addPost(PostVo postVo) {
		return postRepository.insertPost(postVo);
	} 
}
