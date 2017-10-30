package org.estore.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;

/** 
 * we make use of the Spring Boot ConfigurationProperties. 
 * This will tell Spring Boot to automatically map properties found in the classpath 
 * (by default, in the application.properties file) in a type-safe fashion.
 * */
@Configuration
//@PropertySource("classpath:configprops.properties")
@ConfigurationProperties(prefix = "upload.pictures")
public class PictureConfig {
	private Resource uploadPath;
	private Resource anonymousPicture;

	public Resource getAnonymousPicture() {
		return anonymousPicture;
	}

	/** 
	 * Because we use Spring's DefaultResourceLoader class, we can use prefixes such as file: or classpath: 
	 * to specify where our resources can be found. 
	 * This would be the equivalent of creating a FileSystemResource class or a ClassPathResource class.
	 * */
	public void setAnonymousPicture(String anonymousPicture) throws IOException {
		this.anonymousPicture = new DefaultResourceLoader().getResource(anonymousPicture);
		if (!this.anonymousPicture.getFile().isFile()) {
			throw new IOException("File " + anonymousPicture + " does not exist");
		}
	}

	public Resource getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) throws IOException {
		this.uploadPath = new DefaultResourceLoader().getResource(uploadPath);
		if (!this.uploadPath.getFile().isDirectory()) {
			throw new IOException("Directory " + uploadPath + " does not exist");
		}
	}
}
