package com.team7.appointmentsystem.controllers;

import com.team7.appointmentsystem.entity.Users;
import com.team7.appointmentsystem.exceptions.UserNotFoundException;
import com.team7.appointmentsystem.models.UserInfo;
import com.team7.appointmentsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilityController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/user/getUserInfo/", params = "email")
    public ResponseEntity<UserInfo> getUserInfo(@RequestParam String email) throws UserNotFoundException {
        Users user = userRepository.findByEmail(email);
        try{
            if(user == null) {
                throw new UserNotFoundException("Request data not found! Check email is correct or not?");
            }
        }catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getUserid());
        userInfo.setEmail(user.getEmail());
        userInfo.setMobileNumber(user.getMobileNumber());
        userInfo.setName(user.getFirstName() + " "+ user.getLastName());
        return ResponseEntity.ok(userInfo);
    }

}
