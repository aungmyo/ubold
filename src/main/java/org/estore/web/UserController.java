package org.estore.web;

import org.estore.domain.Members;
import org.estore.domain.Users;
import org.estore.persistence.MemberRepository;
import org.estore.persistence.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MemberRepository memberRepository;

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
//		User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());

		userRepository.save(new Users(user.getUsername(), encodedPassword, user.getEmail(), true));
		memberRepository.save(new Members(user.getUsername(), 1));

		log.info("New account created.");
		return "redirect:/dashboard";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupForm() {
		return "signup";
	}

}