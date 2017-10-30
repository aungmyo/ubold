package org.estore.web;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.estore.config.PictureConfig;
import org.estore.config.ProfileSessionConfig;
import org.estore.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.util.Locale;

@Controller
public class AccountPicController {
	private final Resource uploadPath;
	private final Resource anonymousPicture;
	private final MessageSource messageSource;
	private final ProfileSessionConfig profileSession;
	private String profilePic;

	@Autowired
	public AccountPicController(PictureConfig pictureConfig, MessageSource messageSource,
								   ProfileSessionConfig profileSession) {
		uploadPath = pictureConfig.getUploadPath();
		anonymousPicture = pictureConfig.getAnonymousPicture();
		this.messageSource = messageSource;
		this.profileSession = profileSession;
	}

	@RequestMapping(value = "/uploadedPicture")
	public void getUploadedPicture(HttpServletResponse response) throws IOException {
		profilePic = "pictures/pic1992877118091859605.jpg";
		Resource sessionPic = profileSession.getPicturePath();
		if (sessionPic == null) {
			if (profilePic == null) {
				sessionPic = anonymousPicture;
			} else {
				sessionPic = new FileSystemResource(ResourceUtils.getFile(profilePic).getAbsoluteFile());
			}
		}
		response.setHeader("Content-Type", URLConnection.guessContentTypeFromName(sessionPic.getFilename()));
		IOUtils.copy(sessionPic.getInputStream(), response.getOutputStream());
	}

	@RequestMapping(value = "/profile", params = { "upload" }, method = RequestMethod.POST)
	public String onUpload(@RequestParam MultipartFile file, RedirectAttributes redirectAttrs) throws IOException {
		if (file.isEmpty() || !isImage(file)) {
			redirectAttrs.addFlashAttribute("error", "Incorrect file. Please upload a picture.");
			return "redirect:/profile";
		}
		Resource picturePath = copyFileToPictures(file);
		profileSession.setPicturePath(picturePath);
		return "redirect:profile";
	}

	private Resource copyFileToPictures(MultipartFile file) throws IOException {
		String fileExtension = getFileExtension(file.getOriginalFilename());
		File tempFile = File.createTempFile("pic", fileExtension, uploadPath.getFile());
		try (InputStream in = file.getInputStream(); OutputStream out = new FileOutputStream(tempFile)) {

			IOUtils.copy(in, out);
		}
		return new FileSystemResource(tempFile);
	}

	@ExceptionHandler(IOException.class)
	public ModelAndView handleIOException(Locale locale) {
		ModelAndView modelAndView = new ModelAndView("profile");
		modelAndView.addObject("error", messageSource.getMessage("upload.io.exception", null, locale));
		modelAndView.addObject("account", profileSession.displayForm(new Account()));
		return modelAndView;
	}

	@RequestMapping("/uploadError")
	public ModelAndView onUploadError(Locale locale) {
		ModelAndView modelAndView = new ModelAndView("profile");
		modelAndView.addObject("error", messageSource.getMessage("upload.file.too.big", null, locale));
		modelAndView.addObject("account", profileSession.displayForm(new Account()));
		return modelAndView;
	}

	private boolean isImage(MultipartFile file) {
		return file.getContentType().startsWith("image");
	}

	private static String getFileExtension(String name) {
		return name.substring(name.lastIndexOf("."));
	}
}
