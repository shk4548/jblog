package com.poscoict.jblog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscoict.jblog.service.UserService;
import com.poscoict.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
		@Autowired
		private UserService userService;
		
		@RequestMapping(value="/join", method=RequestMethod.GET)
		public String join() {
			return "user/join";
		}
		
		@RequestMapping(value="/join", method=RequestMethod.POST)
		public String join( UserVo vo, BindingResult result, Model model) {
			
			if(result.hasErrors()) {
				model.addAllAttributes(result.getModel());
				return "user/join";
			}
			
			userService.join(vo);
			return "redirect:/user/joinsuccess";
		}
		
		@RequestMapping(value="/joinsuccess")
		public String joinsuccess() {
			return "user/joinsuccess";
		}	
		
		@RequestMapping(value="/login")
		public String login() {
			return "user/login";
		}		
		// 인터셉터 처리
		@RequestMapping(value = "/auth", method = RequestMethod.POST)
		public void auth() {
		}
		@RequestMapping(value = "/logout", method = RequestMethod.GET)
		public void logout() {
		}
		
		
}	

