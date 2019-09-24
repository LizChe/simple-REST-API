package com.hsbc.simpleapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsbc.simpleapi.model.Customer;
import com.hsbc.simpleapi.dao.CustomerRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer insert(Customer customer) {
        return customerRepository.save(customer);
    }
}