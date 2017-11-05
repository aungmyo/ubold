package org.estore.persistence;

import org.estore.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRepository extends JpaRepository<Account, String> {

//	Iterable<User> findByUsernameIgnoringCase(String username);

	@Transactional(readOnly = true)
	Account findByUsernameIgnoringCase(String username);

//	Account findByEmail(String email);

	@Transactional
	@Modifying
	@Query("Update users u set u.email = :email, u.firstName = :firstName, u.lastName = :lastName, u.photo = :photo, u.biography = :biography, u.phoneNumber = :phoneNumber WHERE u.username = :username")
	void updateProfile(@Param("username") String name, @Param("email") String email,
			@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("photo") String photo,
			@Param("biography") String bio, @Param("phoneNumber") String phone);

}