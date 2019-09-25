package com.hsbc.simpleapi.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.web.server.ResponseStatusException;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.mockito.Mockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import com.hsbc.simpleapi.model.Customer;
import com.hsbc.simpleapi.service.CustomerService;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class CustomerControllerTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    private Customer dummyCustomer;

    private final String urlGetTemplateWithId = "/api/customer/{id}";
    private final String urlPostTemplate = "/api/customer";
    private final String dummyCustomerToJson = "{\"id\":0,\"name\":\"Dummy\",\"address\":null}";

    @BeforeEach
    void createDummyCustomer() {
        dummyCustomer = new Customer();
        dummyCustomer.setId(0L);
        dummyCustomer.setName("Dummy");
        dummyCustomer.setAddress(null);
    }

    @Test
    void shouldReturnHttpStatusCreatedWhenCustomerIsCreated() throws Exception {
        given(customerService.insert(any(Customer.class))).willReturn(dummyCustomer);
        mockMvc.perform(post(urlPostTemplate)
                .content(dummyCustomerToJson)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(0));
    }

    @Test
    void shouldReturnHttpStatusOkWhenCustomerByIdIsFound() throws Exception {
        given(customerService.getCustomerBy(0L)).willReturn(any(Customer.class));
        this.mockMvc.perform(get(urlGetTemplateWithId, 0L)).andExpect(status().isOk());
    }

    @Test
    void shouldReturnHttpStatusNotFoundWhenCustomerByIdDoesNotExist() throws Exception {
        when(customerService.getCustomerBy(0L)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));
        mockMvc.perform(get(urlGetTemplateWithId, 0L)).andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCorrectContentOfJsonTypeWhenCustomerByIdIsCalled() throws Exception {
        given(customerService.getCustomerBy(0L)).willReturn(dummyCustomer);
        mockMvc.perform(get(urlGetTemplateWithId, 0L))
                .andExpect(content().json(dummyCustomerToJson))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }
}