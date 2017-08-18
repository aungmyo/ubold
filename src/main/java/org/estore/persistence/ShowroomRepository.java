package org.estore.persistence;

import org.estore.domain.Showroom;
import org.springframework.data.repository.CrudRepository;

public interface ShowroomRepository extends CrudRepository<Showroom, Long> {

	Iterable<Showroom> findByManager(String string);

}
