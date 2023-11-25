package com.example.customer.config;

import com.example.library.model.Customer;
import com.example.library.model.Role;
import com.example.library.repository.CustomerRepository;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.customer.exception.CustomerBlockedException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CustomerDetailsService implements UserDetailsService {

    private CustomerRepository customerRepository;


    public CustomerDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email);


        if(customer !=null) {
            if (customer.is_activated()) {

                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(customer.getRoles()));

                return new CustomerDetails(
                        customer.getEmail(),
                        customer.getPassword(),
                        authorities,
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getMobileNumber(),
                        customer.is_activated());


            } else {

                throw new CustomerBlockedException("Your account has been blocked. Please contact support.");
            }
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

}
