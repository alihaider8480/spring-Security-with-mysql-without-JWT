package com.example.practice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.practice.dto.User;

public interface UserRepo extends JpaRepository<User, Integer>
{
	User findByEmail(String email);
}
