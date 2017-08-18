package org.estore.persistence;

import org.estore.domain.Members;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Members, Long> {

}
