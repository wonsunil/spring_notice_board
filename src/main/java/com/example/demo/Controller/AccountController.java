package com.example.demo.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Function;
import com.example.demo.Model.User;
import com.example.demo.Services.AccountService;

@Controller
public class AccountController {
	private final AccountService accountService;
	private final Function function = new Function();
	
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	};
	
	@GetMapping("/register")
	public String register() {
		return "pages/account/register";
	};
	
	@PostMapping("/register")
	public void register(@ModelAttribute User user, HttpServletResponse response) throws IOException {
		Boolean result = accountService.register(user);
		
		if(result) {
			function.alert("회원가입되었습니다.", "/index", response);			
		}else {
			function.alert("중복된 아이디입니다.", "/register", response);
		};
	};
	
	@GetMapping("/login")
	public String login() {
		return "pages/account/login";
	};
	
	@PostMapping("/login")
	public void login(Model model, @ModelAttribute User user, HttpSession session, HttpServletResponse response) throws IOException {
		 User findUser = accountService.login(user);
		 if(!Objects.isNull(findUser)) {
			 model.addAttribute("user", findUser);
			 
			 session.setAttribute("user", findUser);
			 
			 function.alert("로그인에 성공하였습니다", "/", response);
		 }else {
			 function.alert("아이디 또는 비밀번호가 일치하지 않습니다", "/login", response);
		 };
	};
	
	@GetMapping("/user/logout")
	public String logout(HttpSession session) {
		session.setAttribute("user", null);
		
		return "redirect:/";
	};
	
	@GetMapping("/user/{id}/profile")
	public String profilePage(@PathVariable(value = "id") String id, Model model) {
		User user = accountService.findById(id).get();
		model.addAttribute("user", user);
		
		return "pages/account/profile";
	};

	@GetMapping("/users")
	@ResponseBody
	public List<User> getAllUsers() {
		return accountService.findAll();
	};
}
