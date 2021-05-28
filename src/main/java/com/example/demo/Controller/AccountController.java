package com.example.demo.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    }

    @GetMapping("/register")
    public String register() {
        return "pages/account/register";
    }

    @PostMapping("/register")
    public void register(@ModelAttribute User user, HttpServletResponse response) throws IOException {
        Boolean result = accountService.register(user);

        if (result) {
            function.alert("회원가입되었습니다.", "/index", response);
        } else {
            function.alert("중복된 아이디입니다.", "/register", response);
        }
    }

    @GetMapping("/login")
    public String login() {
        return "pages/account/login";
    }

    @PostMapping("/user/{id}/check")
    @ResponseBody
    public Boolean userIdExistenceCheck(@PathVariable(value = "id") String id) {
        Optional<User> user = accountService.findById(id);

        return user.isPresent();
    }

    @PostMapping("/login-check")
    public Boolean loginCheck(@ModelAttribute User user) {
        return !Objects.isNull(accountService.login(user));
    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute User user, HttpSession session, HttpServletResponse response) throws IOException {
        User findUserById = accountService.findById(user.getId()).orElse(null);

        if(findUserById == null) {
            function.alert("존재하지 않는 아이디입니다", "/login", response);
        }

        User findUser = accountService.login(user);

        if(findUser == null) {
            function.alert("비밀번호가 틀렸습니다", "/login", response);
        }

        model.addAttribute("user", findUser);

        session.setAttribute("user", findUser);

        return "redirect:/";
    }

    @GetMapping("/user/logout")
    public String logout(HttpSession session) {
        session.setAttribute("user", null);

        return "redirect:/";
    }

    @GetMapping("/user/{id}/profile")
    public String profilePage(@PathVariable(value = "id") String id, Model model) {
        User user = accountService.findById(id).orElse(null);
        model.addAttribute("findUser", user);

        return "pages/account/profile";
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> getAllUsers() {
        return accountService.findAll();
    }
}
