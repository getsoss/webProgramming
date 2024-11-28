package com.example.webProgramming;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Spring이 관리하는 Repository Bean
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository<T, ID>: CRUD 메서드 제공
}
