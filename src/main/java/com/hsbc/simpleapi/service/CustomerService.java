package com.hsbc.simpleapi.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsbc.simpleapi.model.Customer;
import com.hsbc.simpleapi.dao.CustomerRepository;

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

    public Customer getCustomerBy(long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer under given id does not exist."));
    }
}