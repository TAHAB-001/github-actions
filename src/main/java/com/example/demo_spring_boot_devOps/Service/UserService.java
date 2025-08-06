package com.example.demo_spring_boot_devOps.Service;

import com.example.demo_spring_boot_devOps.Entity.User;
import com.example.demo_spring_boot_devOps.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }
}
