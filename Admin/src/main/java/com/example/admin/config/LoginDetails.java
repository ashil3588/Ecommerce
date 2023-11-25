//package com.example.admin.config;
//
//import com.example.library.model.Admin;
//import com.example.library.repository.AdminRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class LoginDetails implements UserDetailsService {
//
//    @Autowired
//    private AdminRepository adminRepository;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        String userName;
//        Admin admin = adminRepository.findByName(username);
//        userName = admin.getName();
//
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(admin.getRole()));
//
//        return  new CustomAdmin(userName,admin.getPassword(),authorities,
//                admin.getEmail(),admin.getName(),
//                admin.getMobile(),admin.getId());
//
//    }
//}
