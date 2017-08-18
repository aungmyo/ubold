package org.estore.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class Users implements Serializable {

	/** The Serial Version UID for Serializable classes. */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true)
	private String username;

	private String password;

	@Column(unique = true, nullable = false)
	private String email;

	private String firstName;

	private String lastName;

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	private boolean enabled;

	public Users(String username, String password, String email, boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
	}

}