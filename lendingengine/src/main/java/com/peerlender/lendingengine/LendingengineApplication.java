package com.peerlender.lendingengine;

import com.peerlender.lendingengine.domain.model.AppUsers;
import com.peerlender.lendingengine.domain.model.Balance;
import com.peerlender.lendingengine.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LendingengineApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(LendingengineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		userRepository.save(new AppUsers("John", "John", "Omollo", 27, "Credit",new Balance()));
		userRepository.save(new AppUsers("Mark", "Mark", "Yashmak", 29, "Software Engineer",new Balance()));
		userRepository.save(new AppUsers("James","James", "Putin", 35,"SalesMan",new Balance()));
	}
}
