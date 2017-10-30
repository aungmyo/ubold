package org.estore.persistence;

import org.estore.domain.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRepository extends CrudRepository<Account, String> {

//  Iterable<User> findByUsernameIgnoringCase(String username);

	@Transactional(readOnly = true)
	Account findByUsernameIgnoringCase(String username);

//	Account findByEmail(String email);

	@Transactional
	@Modifying
	@Query("update users u set u.email = :email where u.username = :username")
	void updateProfile(@Param("username") String username, @Param("email") String email);

}