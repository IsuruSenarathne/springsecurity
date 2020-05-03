package com.springtraining.springsecurity.service;

import com.springtraining.springsecurity.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    public List<Customer> getAllCustomers();
    public Customer getCustomerById(int id);

}
