package org.estore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UBold {

/**	@Bean
	public InitializingBean seedDatabase(ShowroomRepository repository) {
		return () -> {
			// Create and populate cars set
			Set<Car> cars = new HashSet<Car>();

			cars.add(new Car("Toyota", "Racing Green"));
			cars.add(new Car("Nissan", "White"));
			cars.add(new Car("BMW", "Black"));
			cars.add(new Car("BMW", "Black"));

			repository.save(new Showroom(cars, "Josh Long", "East Croydon, Greater London"));
		};
	}

	@Bean
	public CommandLineRunner exampleQuery(ShowroomRepository repository) {
		return args -> repository.findByManager("Josh Long").forEach(System.err::println);
	}*/

	public static void main(String[] args) {
		SpringApplication.run(UBold.class, args);
	}
}