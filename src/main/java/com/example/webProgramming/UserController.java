package com.example.webProgramming;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // google.maps.api.key를 application.properties에서 읽어오기
    @Value("${google.maps.api.key}")
    private String googleMapsApiKey;

    // 모든 유저 조회 화면
    @GetMapping
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user-list";
    }

    // 새 유저 추가 화면
    @GetMapping("/new")
    public String newUserForm(Model model) {
        User user = new User(); // 빈 객체 생성
        user.setName("");       // 기본값 설정
        user.setEmail("");
        user.setPhone("");
        user.setAddress("");
        model.addAttribute("user", user);
        model.addAttribute("googleMapsApiKey", googleMapsApiKey); // 구글 맵 API 키 전달
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
        model.addAttribute("googleMapsApiKey", googleMapsApiKey); // 구글 맵 API 키 전달
        return "user-form";
    }

    // 유저 삭제 처리
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
