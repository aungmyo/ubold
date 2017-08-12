package com.windy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.windy.domain.User;
import com.windy.persistence.UserRepository;

@Controller
public class LoginController {

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
	public String register(@ModelAttribute User user) {
//		User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		user.setUsername("spring");
		user.setPassword("b8ff599d21dd1f4f4631172a1fd2c561ccd254128208e88576aec785a0af3697015182dfd6020edc");
		user.setEmail("aung@gmail.com");
		user.isEnabled();
		repo.save(user);

		return "redirect:/";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String goRegister() {
		return "register";
	}
}