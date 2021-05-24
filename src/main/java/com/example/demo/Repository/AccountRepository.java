package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.User;

@Repository
public interface AccountRepository extends JpaRepository<User, String> {
	public List<User> findAll();
	public User findByIdAndPassword(String id, String password);
}
