package com.mole.springbootexample.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    List<Customer> selectAllCustomers();
    Optional<Customer> selectCutosmerById(Integer id);
    void insertCustomer(Customer customer);
    boolean existsPersonWithEmail(String email);
}
