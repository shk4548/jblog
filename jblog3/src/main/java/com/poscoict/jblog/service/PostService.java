package com.poscoict.jblog.service;

import java.util.List;

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

	public List<PostVo> getPostList(Long category_no) {
//		PostVo postVo = postRepository.findPost(post_no);
		return postRepository.viewPost(category_no);
	}

	public PostVo getPost(Long postNo) {
		
		return postRepository.findPost(postNo);
	} 
}
