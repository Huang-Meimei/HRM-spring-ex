package com.mole.springbootexample;

import com.mole.springbootexample.customer.Customer;
import com.mole.springbootexample.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringBootExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootExampleApplication.class, args);
    }

    record GreetResponse(String greet){}

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository){
        return args -> {

            Customer Alax = new Customer("Alax","111@qq.com",12);
            Customer Jane = new Customer("Jane","112@qq.com",12);
            List<Customer> customers= List.of(Alax,Jane);
            customerRepository.saveAll(customers);

        };
    }
}
