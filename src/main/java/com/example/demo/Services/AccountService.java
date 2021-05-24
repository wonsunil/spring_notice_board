package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.AccountRepository;

@Service
public class AccountService {
	final private AccountRepository accountRepository;
	
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	};
	
	public List<User> findAll() {
		return accountRepository.findAll();
	};
	
	public Optional<User> findById(String id) {
		return accountRepository.findById(id);
	};

	public Boolean register(User user) {
		if(!accountRepository.findById(user.getId()).isEmpty()) {
			return false;
		};
		
		accountRepository.save(user);
		
		return true;
	};
	
	public User login(User user) {
		return accountRepository.findByIdAndPassword(user.getId(), user.getPassword());
	};
}
