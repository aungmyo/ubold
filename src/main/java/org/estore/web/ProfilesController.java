package org.estore.web;

import org.estore.config.ProfileSessionConfig;
import org.estore.domain.Account;
import org.estore.persistence.AccountRepository;
import org.estore.persistence.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class ProfilesController {

	private static final Logger log = LoggerFactory.getLogger(ProfilesController.class);
	private ProfileSessionConfig profileSession;
	private AccountRepository accountRepository;
	private AddressRepository addressRepository;
	private String photo;

	@Autowired
	public ProfilesController(ProfileSessionConfig profileSession, AccountRepository accountRepository,
					AddressRepository addressRepo) {
		this.profileSession = profileSession;
		this.accountRepository = accountRepository;
		this.addressRepository = addressRepo;
	}

	@ModelAttribute
	public Account profileForm() {
		Account profile = new Account();
		profile = accountRepository.findByUsernameIgnoringCase(profileSession.getUserName());
		return profileSession.displayForm(profile);
	}

	@RequestMapping("/profile")
	public String displayProfile(Account profileForm) {
		return "profile";
	}

	@RequestMapping(value = "/profile", params = { "update" }, method = RequestMethod.POST)
	public String updateProfile(@Valid Account account, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "profile";
		}

		if (profileSession.getPicturePath() != null) {
			photo = "pictures/" + profileSession.getPicturePath().getFilename();
			log.info("picturePath: " + photo);
		}

		accountRepository.updateProfile(profileSession.getUserName(), account.getEmail(), account.getFirstName(),
				account.getLastName(), photo, account.getBiography(), account.getPhoneNumber());

		addressRepository.updateAddress(profileSession.getId(), account.getAddress().getAddress(),
				account.getAddress().getCity(), account.getAddress().getPostalCode(), account.getAddress().getState());

		log.info("Your information has been saved.");

		profileSession.updateForm(account);
		return "redirect:/profile";
	}
}
