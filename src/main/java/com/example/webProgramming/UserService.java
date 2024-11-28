package com.example.webProgramming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // 비즈니스 로직을 처리하는 서비스 클래스
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 모든 유저 조회
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ID로 유저 조회
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // 유저 생성 또는 업데이트
    public User saveOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    // 유저 삭제
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
