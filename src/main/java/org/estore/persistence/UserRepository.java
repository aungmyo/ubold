package org.estore.persistence;

import org.estore.domain.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, String> {

	// Iterable<User> findByUsernameIgnoringCase(String username);

	Users findByUsernameIgnoringCase(String username);

	Users findByEmail(String email);

	// @Transactional
	// @Modifying
	// @Query("update User u set u.password = :password where u.id = :userId")
	// void updateUserPassword(@Param("userId") long userId, @Param("password")
	// String password);

}
