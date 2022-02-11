package com.poscoict.jblog.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.vo.BlogVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private FileUploadService fileUploadService;
	@Autowired
	private HttpSession session;
	

	// 보기
	public BlogVo getBlog(String id) {
		BlogVo blogVo = blogRepository.viewBlog(id);
		return blogVo;
	}
		
		
	public Boolean updateBlog(BlogVo blogVo, MultipartFile multipartFile,String id) {
		
		String url = fileUploadService.restore(multipartFile);
		if(url != null) {
			blogVo.setLogo(url);
		}
		
		if(blogRepository.updateBlog(blogVo)) {
			session.setAttribute("blogVo", blogRepository.viewBlog(blogVo.getUser_id()));
			return true;
		}else {
			return false;
		}
	}
}
