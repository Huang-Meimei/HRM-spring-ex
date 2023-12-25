package com.mole.springbootexample.customer;

import com.mole.springbootexample.exception.DuplicateResourceException;
import com.mole.springbootexample.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDAO customerDAO;

    public CustomerService(@Qualifier("jpa") CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }


    public List<Customer> getAllCustomers(){
        return customerDAO.selectAllCustomers();
    }


    public Customer getCustomerById(Integer customerId) {
        return customerDAO.selectCutosmerById(customerId)
                .orElseThrow(()->new ResourceNotFound("customer with id [%s] not found".formatted(customerId)));
    }

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest){

        if (customerDAO.existsPersonWithEmail(customerRegistrationRequest.email())){
            throw new DuplicateResourceException(
                    "email already taken"
            );


        }
        customerDAO.insertCustomer(
                new Customer(
                        customerRegistrationRequest.name(),
                        customerRegistrationRequest.email(),
                        customerRegistrationRequest.age()
                )
        );

    }
}
