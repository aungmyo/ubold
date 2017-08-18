package org.estore.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Showroom implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "showroom_id")
	@GeneratedValue
	private Long id;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "showroom_id")
	private Set<Car> cars;
//  private String[] cars;

	private String manager;

	private String location;

	public Showroom(Set<Car> cars, String manager, String location) {
		super();
		this.cars = cars;
		this.manager = manager;
		this.location = location;
	}

}
