package com.example.crudwithvaadin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudWithVaadinApplication {

	private static final Logger log = LoggerFactory.getLogger(CrudWithVaadinApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CrudWithVaadinApplication.class);
	}

	@Bean
	public CommandLineRunner loadData(CustomerRepository repository) {
		return (args) -> {
			repository.save(new Customer("Douglas", "Grant"));
			repository.save(new Customer("Jennifer", "Whalen"));
			repository.save(new Customer("David", "Austin"));
			repository.save(new Customer("Adam", "Fripp"));
			repository.save(new Customer("Alexander", "Khoo"));

			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			Customer customer = repository.findById(1L).get();
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			log.info("Customer found with findByLastNameStartsWithIgnoreCase('Grant'):");
			log.info("--------------------------------------------");
			for (Customer cust1 : repository
					.findByLastNameStartsWithIgnoreCase("Grant")) {
				log.info(cust1.toString());
			}
			log.info("");
		};
	}

}
