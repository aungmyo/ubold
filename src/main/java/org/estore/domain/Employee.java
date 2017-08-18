package org.estore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Employee {

	@Id
	@GeneratedValue
	private long id;

	private String name;

	@ManyToOne
	private Department department;

}
