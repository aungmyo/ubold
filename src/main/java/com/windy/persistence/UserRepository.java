package com.windy.persistence;

import org.springframework.data.repository.CrudRepository;
import com.windy.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

	// Iterable<User> findByUsernameIgnoringCase(String username);

	User findByUsernameIgnoringCase(String username);

	User findByEmail(String email);

	// @Transactional
	// @Modifying
	// @Query("update User u set u.password = :password where u.id = :userId")
	// void updateUserPassword(@Param("userId") long userId, @Param("password")
	// String password);

}
