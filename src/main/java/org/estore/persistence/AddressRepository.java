package org.estore.persistence;

import org.estore.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AddressRepository extends JpaRepository<Address, Long> {

	@Transactional
	@Modifying
	@Query("Update address a set a.address = :address, a.city = :city, a.postalCode = :postcode, a.state = :state WHERE a.id = :id")
	void updateAddress(@Param("id") Long id, @Param("address") String address, @Param("city") String city,
			@Param("postcode") String postcode, @Param("state") String state);

}