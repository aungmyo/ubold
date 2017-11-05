package org.estore.persistence;

import org.estore.domain.Showroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowroomRepository extends JpaRepository<Showroom, Long> {

	Iterable<Showroom> findByManager(String string);

}
