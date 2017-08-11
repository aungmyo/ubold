package com.windy.web;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}

	/**
	 * import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
	 * @EnableGlobalMethodSecurity(securedEnabled = true)
	 * */
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView signup() {
		ModelAndView modelAndView = new ModelAndView("signup");
		return modelAndView;
    }

}
