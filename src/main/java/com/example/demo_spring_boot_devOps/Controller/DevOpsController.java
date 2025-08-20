package com.example.demo_spring_boot_devOps.Controller;

import com.example.demo_spring_boot_devOps.Entity.User;
import com.example.demo_spring_boot_devOps.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DevOpsController {

    @Autowired
    private UserService userService;
    @GetMapping("get-data")
    public String getData(){
        return "Hello DevOps hello buddy?";
    }

    @PostMapping("save-user")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }
}
