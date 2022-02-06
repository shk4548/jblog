package com.poscoict.jblog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.jblog.security.Auth;
import com.poscoict.jblog.security.AuthUser;
import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.service.CategoryService;
import com.poscoict.jblog.service.PostService;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;
import com.poscoict.jblog.vo.UserVo;

@RequestMapping({"/{id}"})
@Controller
public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
//	@Autowired
//	private UserService userService;
	
	// main
	@RequestMapping("")
	public String blog(@PathVariable("id") String id) {
		return "blog/blog-main";
	}
	
	// 블로그
	@Auth
	@RequestMapping("admin/basic")
	public String blogBasic(@PathVariable("id") String id,@AuthUser UserVo authUser) {
		
		blogService.getBlog(authUser.getId());
		
		return "blog/blog-admin-basic";
	}
	
	@Auth
	@RequestMapping(value = "blog/update", method = RequestMethod.POST)
	public String blogBasic(@PathVariable("id") String id,@AuthUser UserVo authUser
			, BlogVo blogVo , @RequestParam(value = "inputFile") MultipartFile multipartFile) {
		blogVo.setUser_id(authUser.getId());
		System.out.println("아ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ"+blogVo);
		blogService.updateBlog(blogVo, multipartFile, id);
		System.out.println("bhhhhhhhhhhhhhㅏ"+blogVo);

		return "redirect:/blog";
	}
	
	
	// 카테고리
	
	@Auth
	@RequestMapping( value ="admin/category", method =RequestMethod.GET)
	public String blogCategory(@PathVariable("id") String id ,Model model, @AuthUser UserVo authUser) {
		List<CategoryVo> categoryList = categoryService.getCategoryList(authUser.getId());
		model.addAttribute("categoryList", categoryList);
		List<Long> numberList = new ArrayList<>();
		for(CategoryVo vo : categoryList) {
			Long category_no = categoryService.getCountPost(vo.getNo());
			numberList.add(category_no);
		}
		model.addAttribute("nolist",numberList);
		
		return "blog/blog-admin-category";
	}
	
	@Auth
	@RequestMapping(value = "admin/category", method = RequestMethod.POST)
	public String categoryInsert(@PathVariable("id") String id , @AuthUser UserVo authUser, 
								CategoryVo categoryVo) {
		categoryVo.setBlog_id(authUser.getId());
		categoryService.addCategory(categoryVo);
		return "redirect:/blog";
	}
	
	@Auth
	@RequestMapping(value = "delete/{no}", method = RequestMethod.GET)
	public String categoryDelete( @PathVariable("id") String id, @PathVariable("no") Long category_no){
		boolean result = categoryService.deleteCategory(category_no);
		return "redirect:/blog";
	}
	
	// 포스트
	@Auth
	@RequestMapping(value = "admin/post", method = RequestMethod.GET)
	public String postInsert(@PathVariable("id") String id , @AuthUser UserVo authUser, Model model) {
		List<CategoryVo> categoryList = categoryService.getCategoryList(authUser.getId());
		model.addAttribute("categoryList", categoryList);		
		return "blog/blog-admin-write";
	}
	
	@Auth
	@RequestMapping(value="admin/post", method = RequestMethod.POST)
	public String postInsert(@PathVariable("id") String id , @AuthUser UserVo authUser, PostVo postVo
			,@RequestParam(value="category", required=true, defaultValue = "default") String category,Model model) {
		postVo.setCategory_no(postService.getCategory(category, authUser.getId()).getNo());
		postService.addPost(postVo);
		return "redirect:/blog";
	}
	
	
	
	
	
	// view - 카테고리
	
	// view - 글 목록
	
	// view - 가장 최신글
	
	// view - blog
	
	
	
	
	
	
}
