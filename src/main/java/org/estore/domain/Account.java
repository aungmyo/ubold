package org.estore.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@Entity(name = "users")
@NoArgsConstructor
@EqualsAndHashCode(exclude={"address"})
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true)
	private String username;

	private String password;

	/**
	 * a LocalDate class is a simple day without time associated to it.
	 * it can be differentiated from the LocalTime class, which represents a time within a day.
	 * the LocalDateTime class, which represents both, or the ZonedDateTime class, which uses a time zone.
	 * 
	 * learn more about the Java 8 date time API, https://docs.oracle.com/javase/tutorial/datetime/TOC.html.
	 * */
//	private LocalDate birthDate;

	private String firstName;

	private String lastName;

	private String phoneNumber;

	@Column(unique = true, nullable = false)
	private String email;

	private String photo;

	private String biography;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="address_id")
	private Address address;

	private boolean enabled;

}