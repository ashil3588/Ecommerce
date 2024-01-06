package com.example.customer.controller;

import com.example.library.dto.CustomerDto;
import com.example.library.model.Customer;
import com.example.library.repository.CustomerRepository;
import com.example.library.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {



    private CustomerService customerService;

    private CustomerRepository customerRepository;

    @Autowired
    public LoginController(CustomerService customerService,
                           CustomerRepository customerRepository) {
        this.customerService=customerService;
        this.customerRepository=customerRepository;
    }

    @GetMapping("/login")
    public String getLoginForm(Model model,HttpSession session){

        model.addAttribute("title", "Login Page");
        Object attribute = session.getAttribute("userLoggedIn");
        if(attribute!=null){
            return "redirect:/dashboard";
        }
        return "login";
    }






    @GetMapping("/register")
    public String getRegisterForm(Model model){

        model.addAttribute("title","Register");
        model.addAttribute("customerDto", new CustomerDto());

        return "register";
    }

    @PostMapping("/do-register")
    public String registerCustomer(@Valid @ModelAttribute("customerDto") CustomerDto customerDto,
                                   BindingResult result,
                                   Model model) {

        try {
            if (result.hasErrors()) {
                model.addAttribute("customerDto", customerDto);
                return "register";
            }
            String username = customerDto.getEmail();
            Customer customer = customerService.findByEmail(username);
            if (customer != null) {
                model.addAttribute("customerDto", customerDto);
                model.addAttribute("error", "This Email is already Registered!");
                return "register";
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Server is error, try again later!");
        }

        customerService.save(customerDto);
        return "redirect:/register?success";

    }








}
