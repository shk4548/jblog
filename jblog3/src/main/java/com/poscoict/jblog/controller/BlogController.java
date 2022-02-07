package com.poscoict.jblog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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

@RequestMapping({"/{id:(?!assets|images).*}"})
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
	
	@Autowired
	private HttpSession session;
	
	// main
	@RequestMapping(value={"", "/{categoryNo}", "/{categoryNo}/{postNo}"})
	public String blog(@AuthUser UserVo authUser, @PathVariable("id") String id, Model model, 
			@PathVariable(required = false) Long categoryNo,
			@PathVariable(required = false) Long postNo) {	
		// 블로그
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo" , blogVo);
		
		// 카테고리 리스트
		Long category_no = categoryNo;
		List<CategoryVo> categoryList = categoryService.getCategoryList(id);

		if (category_no == null) {
			category_no = categoryList.get(0).getNo();
		}
		model.addAttribute("categoryList", categoryList);
		
		// 포스트 리스트
		Long post_no = postNo;
		List<PostVo> postList = postService.getPostList(category_no);

		if (post_no == null && postList.size() >= 1) {
			post_no = postList.get(0).getNo();
		}
		model.addAttribute("postList", postList);
		
		// 포스트 보기
		PostVo postVo = postService.getPost(post_no);
		model.addAttribute("postVo", postVo);		
		return "blog/blog-main";
	}
	
	// 블로그
	@Auth
	@RequestMapping("admin/basic")
	public String blogBasic(@PathVariable("id") String id,@AuthUser UserVo authUser) {
		System.out.println(authUser.getId() + " 세션 아이디  " + id + "받는아이디 씨발거진짜" );
		if(authUser == null || !authUser.getId().equals(id)) {
			return "redirect:/" + authUser.getId();
		}
		blogService.getBlog(id);
		
		return "blog/blog-admin-basic";
	}
	
	@Auth
	@RequestMapping(value = "blog/update", method = RequestMethod.POST)
	public String blogBasic(@PathVariable("id") String id,@AuthUser UserVo authUser
			, BlogVo blogVo , @RequestParam(value = "inputFile") MultipartFile multipartFile) {
		blogVo.setUser_id(authUser.getId());
		blogService.updateBlog(blogVo, multipartFile, blogVo.getUser_id());
		System.out.println("bhhhhhhhhhhhhhㅏ"+blogVo);

		return "redirect:/" +authUser.getId();
	}
	
	
	// 카테고리
	
	@Auth
	@RequestMapping( value ="admin/category", method =RequestMethod.GET)
	public String blogCategory(@PathVariable("id") String id ,Model model, @AuthUser UserVo authUser) {
		if(authUser == null || !authUser.getId().equals(id)) {
			return "redirect:/" + authUser.getId();
		}
		
		List<CategoryVo> categoryList = categoryService.getCategoryList(id);
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
		return "redirect:/" +authUser.getId();
	}
	
	@Auth
	@RequestMapping(value = "delete/{no}", method = RequestMethod.GET)
	public String categoryDelete( @PathVariable("id") String id, @PathVariable("no") Long category_no,@AuthUser UserVo authUser){
		
		if(authUser == null || !authUser.getId().equals(id)) {
			return "redirect:/" + authUser.getId();
		}
		boolean result = categoryService.deleteCategory(category_no);
		return "redirect:/"+ authUser.getId();
	}
	
	// 포스트
	@Auth
	@RequestMapping(value = "admin/post", method = RequestMethod.GET)
	public String postInsert(@PathVariable("id") String id , @AuthUser UserVo authUser, Model model) {
		if(authUser == null || !authUser.getId().equals(id)) {
			return "redirect:/" + authUser.getId();
		}
		List<CategoryVo> categoryList = categoryService.getCategoryList(id);
		model.addAttribute("categoryList", categoryList);		
		return "blog/blog-admin-write";
	}
	
	@Auth
	@RequestMapping(value="admin/post", method = RequestMethod.POST)
	public String postInsert(@PathVariable("id") String id , @AuthUser UserVo authUser, PostVo postVo
			,@RequestParam(value="category", required=true, defaultValue = "default") String category,Model model) {
		
		
		postVo.setCategory_no(postService.getCategory(category, authUser.getId()).getNo());
		postService.addPost(postVo);
		return "redirect:/" +authUser.getId();
		
	}	
	
	
	
}
