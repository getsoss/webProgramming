package com.example.webProgramming;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // Mustache를 위한 컨트롤러 클래스
@RequestMapping("/users") // 기본 URL 경로 설정
public class UserController {

    @Autowired
    private UserService userService;

    // 모든 유저 조회 화면
    @GetMapping
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users); // 모델에 데이터 추가
        return "user-list"; // Mustache 템플릿 이름
    }

    // 새 유저 추가 화면
    @GetMapping("/new")
    public String newUserForm(Model model) {
        User user = new User(); // 빈 객체 생성
        user.setName("");       // 기본값 설정
        user.setEmail("");
        user.setPhone("");
        user.setAddress("");
        model.addAttribute("user", user); // 모델에 추가
        return "user-form";
    }


    // 유저 저장 처리
    @PostMapping
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveOrUpdateUser(user);
        return "redirect:/users"; // 유저 리스트 화면으로 리다이렉트
    }

    // 유저 수정 화면
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        model.addAttribute("user", user);
        return "user-form";
    }

    // 유저 삭제 처리
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}

