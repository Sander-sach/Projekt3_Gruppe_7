package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.model.Customer;
import com.example.projekt3_gruppe_7.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final BaseRepository<Customer> customerRepository;

    @Autowired
    public CustomerService(BaseRepository<Customer> customerRepository) {
        this.customerRepository = customerRepository;
    }

    public boolean create(Customer customer){
        if(!validateEmail(customer.getEmail()) || !validatePhoneNumber(customer.getPhone())){
            return false;
        }
        customerRepository.save(customer);
        return true;
    }

    private boolean validateEmail(String email){
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        if (!email.contains("@")) {
            return false;
        }
        return true;
    }
    private boolean validatePhoneNumber(String phone){
        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }
        return true;
    }

}
