package org.estore.config;

import org.estore.domain.Account;
import org.estore.domain.Address;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

/**
 * We just saw a method to put objects in a session using the @SessionAttributes annotation.
 * This works well within a controller but makes the data difficult to share when spread across multiple controllers.
 * We have to rely on a string to resolve the attribute from its name, which is hard to re-factor.
 * For the same reason, we don't want to manipulate the HttpSession directly.
 * 
 * There is another popular approach when it comes to saving things in a session with Spring: annotate a bean with @Scope("session").
 * You will then be able to inject your session bean in your controllers and other Spring components to either set or retrieve values from it.
 * */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProfileSessionConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String firstname;
	private String lastname;
	private String phone;
	private String email;
	private String biography;
	private URL picturePath;
	private boolean updateFlag;

	private Address address;

	public void updateForm(Account account) {
		this.firstname = account.getFirstName();
		this.lastname = account.getLastName();
		this.phone = account.getPhoneNumber();
		this.email = account.getEmail();
		this.biography = account.getBiography();
		this.address = account.getAddress();
		this.updateFlag = true;
	}

	public Account displayForm(Account profile) {

		if (!updateFlag) {	/* call the initial display form (all properties are null) */
			this.firstname = profile.getFirstName();
			this.lastname = profile.getLastName();
			this.phone= profile.getPhoneNumber();
			this.email = profile.getEmail();
			this.biography = profile.getBiography();
			this.address = profile.getAddress();
		}

		Account account = new Account();
		account.setUsername(getUserName());
		account.setFirstName(firstname);
		account.setLastName(lastname);
		account.setPhoneNumber(phone);
		account.setEmail(email);
		account.setBiography(biography);
		account.setAddress(address);
		return account;
	}

	public Long getId(){
		return address.getId();
	}

	public void setPicturePath(Resource picturePath) throws IOException {
		this.picturePath = picturePath.getURL();
	}

	public Resource getPicturePath() {
		return picturePath == null ? null : new UrlResource(picturePath);
	}

	public String getUserName() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		this.username = user.getUsername();
		return username;
	}
}
