package org.estore.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("welcome");
		registry.addViewController("/dashboard").setViewName("dashboard");
		registry.addViewController("/login").setViewName("login");
//		registry.addViewController("/profile").setViewName("profile");
	}

/*	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer = new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				container.addErrorPages(new ErrorPage(MultipartException.class, "/uploadError"));
			}
		};
		return embeddedServletContainerCustomizer;
	}*/

	/** 
	 * This code creates a new error page, which will be called when a MultipartException happens.
	 * It can also be mapped to an HTTP status.
	 * http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-developing-web-applications.html#boot-features-customizing-embedded-containers.
	 * */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return container -> container.addErrorPages(new ErrorPage(MultipartException.class, "/uploadError"));
	}

}
