package com.example.customer.config;

import com.example.library.model.Customer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;


//CHECK HERE
@Getter
@Setter
public class CustomerDetails extends User implements Serializable {

    private String firstName;

    private String lastName;

    private String mobileNumber;

    private boolean is_activated;



    public CustomerDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String firstName, String lastName, String mobileNumber, boolean is_activated) {
        super(username, password, authorities);
        this.firstName=firstName;
        this.lastName=lastName;
        this.mobileNumber=mobileNumber;
        this.is_activated=is_activated;
    }

}
