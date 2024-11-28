package com.example.webProgramming;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // JPA가 관리할 엔티티 클래스임을 명시
@Table(name = "users") // 테이블 이름 지정
@Data // Lombok: Getter, Setter, toString 등을 자동 생성
@NoArgsConstructor // Lombok: 기본 생성자 생성
@AllArgsConstructor // Lombok: 모든 필드를 받는 생성자 생성
public class    User {

    @Id // Primary Key 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    private Long id;

    @Column(nullable = false) // Not Null 제약 조건
    private String name;

    @Column(nullable = false, unique = true) // Not Null + Unique 제약 조건
    private String email;

    private String phone; // 전화번호는 필수가 아님

    private String address; // 전화번호는 필수가 아님
}
