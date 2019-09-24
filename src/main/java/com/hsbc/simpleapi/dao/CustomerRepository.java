package com.hsbc.simpleapi.dao;

import org.springframework.data.repository.CrudRepository;

import com.hsbc.simpleapi.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}