package org.estore.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Department {

	@Id
	@GeneratedValue
	private long id;

	private String name;

	/**
	 * If you're defining a bidirectional one-to-many mapping, make sure to use the --mappedBy attribute 
	 * so that JPA keeps the relationship defined with two physical tables.
	 * If you forget to specify the --mappedBy setting in the one-to-many mapping, you might find that you'll end up with three tables.
	 */	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
	private Set<Employee> employees = new HashSet<Employee>();

}
