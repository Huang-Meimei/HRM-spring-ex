package com.mole.springbootexample;

import com.mole.springbootexample.customer.Customer;
import com.mole.springbootexample.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringBootExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootExampleApplication.class, args);
    }


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
