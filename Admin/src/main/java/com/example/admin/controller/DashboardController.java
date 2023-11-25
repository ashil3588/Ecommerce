package com.example.admin.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String getIndex(HttpSession session) {
//        Object attribute = session.getAttribute("userLoggedIn");
//        if (attribute != null) {
//            return "redirect:/dashboard";
//        }
        return "index";
    }

}
