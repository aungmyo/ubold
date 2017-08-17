package com.windy.persistence;

import org.springframework.data.repository.CrudRepository;

import com.windy.domain.Members;

public interface MemberRepository extends CrudRepository<Members, Long> {

}
