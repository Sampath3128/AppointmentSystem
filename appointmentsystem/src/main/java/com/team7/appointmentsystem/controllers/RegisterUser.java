package com.team7.appointmentsystem.controllers;


import com.team7.appointmentsystem.configuration.CustomUserDetails;
import com.team7.appointmentsystem.entity.Users;
import com.team7.appointmentsystem.exceptions.UserAlreadyExistsException;
import com.team7.appointmentsystem.miscellinious.UserDetails;
import com.team7.appointmentsystem.models.LoggedInUser;
import com.team7.appointmentsystem.models.StrObject;
import com.team7.appointmentsystem.repository.UserRepository;
import com.team7.appointmentsystem.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
public class RegisterUser {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/showUsers")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<Map<String, Object>> showUsers(){
        return registerService.showUsers();
    }

    @GetMapping("/register")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String signUpForm() {
        return "SignupForm";
    }

    @PostMapping("/register")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<String> registerUser(@RequestBody Users user) throws UserAlreadyExistsException {
        System.out.println(user);
        String msg = registerService.registerUser(user);
        return ResponseEntity.ok(msg);
    }

    @GetMapping(value = "/login-success", params = "username")
    public ResponseEntity<LoggedInUser> loginSuccess(@RequestParam String username) {
        Users loggedInUser = userRepository.findByEmail(username);
        LoggedInUser user = new LoggedInUser(loggedInUser.getFirstName() + " " + loggedInUser.getLastName()
                ,loggedInUser.getEmail(), loggedInUser.getUserid(), loggedInUser.getRole() );
        return ResponseEntity.ok(user);
    }

    @GetMapping("/showUsersNew")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<Map<String, Object>> showUsers(){
        return registerService.showUsers();
    }
}