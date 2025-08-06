package com.example.demo_spring_boot_devOps.Repository;

import com.example.demo_spring_boot_devOps.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
