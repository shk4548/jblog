package com.poscoict.jblog.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.poscoict.jblog.exception.GlobalExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	//로그
		private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);
		
		@ExceptionHandler(Exception.class)
		public String ExceptionHandler(Model model, Exception e) {
			// 1. 로깅
			
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			LOGGER.error(errors.toString());
			
			if (e.toString().contains("NoHandlerFoundException")) {
				return "error/404";
			}
			// 2. 사과 페이지 ( HTML응답, 정상 종료 )
			model.addAttribute("exception", errors.toString());

			return "error/exception";
		}
}
