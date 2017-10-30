package org.estore.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String address;

	private String city;

	private String postalCode;

	private String state;

	@OneToOne
	private Account account;

	public Address(String address, String city, String postalCode, String state, Account account) {
		super();
		this.address = address;
		this.city = city;
		this.postalCode = postalCode;
		this.state = state;
		this.account = account;
	}
}
