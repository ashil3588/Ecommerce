package com.example.customer.controller;

import com.example.library.dto.CustomerDto;
import com.example.library.model.Customer;
import com.example.library.model.UserOtp;
import com.example.library.repository.CustomerRepository;
import com.example.library.service.CustomerService;
import com.example.library.service.MessageService;
import com.example.library.service.impl.CustomerServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class LoginController {

//    @Autowired
//    public  BCryptPasswordEncoder passwordEncoder;

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
//        HttpSession session= request.getSession();
//        Object attribute=session.getAttribute("userLoginID");
//        if(attribute!=null) {
//            return "redirect:/dashboard";
//        }
//        return "login";
//    }
        model.addAttribute("title", "Login Page");
        Object attribute = session.getAttribute("userLoggedIn");
        if(attribute!=null){
            return "redirect:/dashboard";
        }
        return "login";
    }

    @GetMapping("/dashboard")
    public String getDashboard(@RequestParam(required = false) String tab, Model model, Principal principal, HttpSession session){
        if(principal==null){
            return "redirect:/login";
        }else{
            Customer customer = customerService.findByEmail(principal.getName());
            session.setAttribute("userLoggedIn",true);
            session.setAttribute("username", customer.getFirstName() + " " + customer.getLastName());
            if(tab!=null && !tab.isEmpty()) {
                model.addAttribute("openTab", tab);
                System.out.println(tab);
            }else{
                model.addAttribute("openTab", "");
            }
            model.addAttribute("customer",customer);
            model.addAttribute("title","Dashboard");
            return "dashboard";
        }
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
//        return "otp-verify";
    }




//    @PostMapping("/do-register/verify")
//    public String verifyOtp(HttpSession session,
//                            @RequestParam("inputOtp")String inputOtp, Model model){
//
//        CustomerDto customerDto = (CustomerDto) session.getAttribute("customerDto");
//        String otp = (String) session.getAttribute("otp");
//
//        if (customerDto != null && otp.equals(inputOtp)) {
//            // OTP verified, save user details and login user
//            customerDto.setPassword(passwordEncoder.encode(customerDto.getPassword()));
//            customerService.save(customerDto);
//
//        } else {
//            model.addAttribute("error","OTP is not valid");
//
//            return "otp-verify";
//        }
//
//        session.removeAttribute("customerDto");
//        session.removeAttribute("otp");
//
//
//        return "redirect:/login";
//    }



}
