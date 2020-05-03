package com.springtraining.springsecurity.controller;

import com.springtraining.springsecurity.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getCustomer(@PathVariable("id") long id, Model model) {
        throw new CustomerNotFoundException(id);
        return "home";
    }

}
