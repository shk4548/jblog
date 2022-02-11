package com.poscoict.jblog;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class BootInitalize extends SpringBootServletInitializer {  //배포를위해 war로 빌드

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(JBlogApplication.class);
	}

}
