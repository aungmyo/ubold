package org.estore.web;

import org.estore.domain.Members;
import org.estore.domain.Account;
import org.estore.domain.Address;
import org.estore.persistence.MemberRepository;
import org.estore.persistence.AccountRepository;
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
public class AccountController {

	private static final Logger log = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountRepository accountRepository;
	
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
	public String signup(@ModelAttribute Account account) {
		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
		String encodedPassword = encoder.encode(account.getPassword());

//		if (accountRepository.findByEmail(user.getEmail()) != null) {
//			throws EmailExistException;
//		}

		Account user = new Account();
		user.setUsername(account.getUsername());
		user.setPassword(encodedPassword);
		user.setEmail(account.getEmail());
		user.setEnabled(true);

		Address address = new Address("", "", "", "", user);
		user.setAddress(address);

		accountRepository.save(user);
		memberRepository.save(new Members(account.getUsername(), 1));

		log.info("New account created.");
		return "redirect:/dashboard";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupForm() {
		return "signup";
	}

}