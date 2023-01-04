package com.by.securitystudy.repository;

import com.by.securitystudy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// CRUD 함수를 JpaRepository가 들고있음
// @Repository라고 선언해주지 않아도 JpaRepository가 IoC되어있음 (AutoWired가능)
public interface UserRepository extends JpaRepository<User, Integer> {

}
