package org.estore.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Profile {

	private String userName;
	private String firstName;
	private String lastName;
	private String phoneNo;
	private String email;

	private String address;
	private String city;
	private String postCode;
	private String state;

}
