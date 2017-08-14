package com.windy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

/*	@Bean
	public InitializingBean seedDatabase(UserRepository repository) {
		return () -> {
			User user = new User();
			user.setUsername("spring");
			user.setPassword("b8ff599d21dd1f4f4631172a1fd2c561ccd254128208e88576aec785a0af3697015182dfd6020edc");
			user.setEmail("aung@gmail.com");
			user.setEnabled(true);
			repository.save(user);
			System.err.println(repository.findByUsername("spring").toString());
		};
	}

	@Bean
	public CommandLineRunner exampleQuery(UserRepository repository) {
		return args -> repository.findByMakeIgnoringCase("HONDA").forEach(System.err::println);
	}*/

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}