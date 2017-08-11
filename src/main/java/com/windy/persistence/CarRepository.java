package com.windy.persistence;

import org.springframework.data.repository.CrudRepository;

import com.windy.domain.Car;

public interface CarRepository extends CrudRepository<Car, Long> {

	Iterable<Car> findByMakeIgnoringCase(String make);

}
