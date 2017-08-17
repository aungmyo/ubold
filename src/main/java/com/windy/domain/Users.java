package com.windy.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Users implements Serializable {

	/** The Serial Version UID for Serializable classes. */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true)
	private String username;

	@Column(name = "password")
	private String password;

	@Column(unique = true)
	private String email;

	private String firstName;

	private String lastName;

	private String address;

	private boolean enabled;

	public Users() {
	}

	public Users(String username, String password, String email, boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
	}

/**	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
	private List<Appointment> appointments = new ArrayList<Appointment>();

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "USER_ID")
	private Users user;
*/
}
