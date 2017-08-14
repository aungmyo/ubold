package com.windy.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.windy.domain.Users;
import com.windy.persistence.UserRepository;

@Controller
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserRepository repo;

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}

	/**
	 * import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
	 * @EnableGlobalMethodSecurity(securedEnabled = true)
	 * @Secured("ROLE_ADMIN")
	 * */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute Users user) {
		log.info("Invoke the signup form.");
		log.info("User Name: " + user.getUsername());
		log.info("Password: " + user.getPassword());
		log.info("Email: " + user.getEmail());
		
//		User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		user.setUsername("spring");
		user.setPassword("b8ff599d21dd1f4f4631172a1fd2c561ccd254128208e88576aec785a0af3697015182dfd6020edc");
		user.setEmail("aung@gmail.com");
		user.isEnabled();
		repo.save(user);

		return "redirect:/";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupForm() {
		return "signup";
	}
}