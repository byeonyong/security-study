package com.by.securitystudy.controller;

import com.by.securitystudy.model.User;
import com.by.securitystudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping({ "", "/" })
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    public  String user(){
        return "user";
    }

    @GetMapping("/admin")
    public  String admin(){
        return "admin";
    }

    @GetMapping("/manager")
    public  String manager(){
        return "manager";
    }

//    SecurityConfig 클래스 생성전에는 스프링시큐리티가 낚아챘는데 사용후에는 정상적으로 이 컨트롤러를 사용함 
    @GetMapping("/loginForm")
    public  String loginForm(){
        return "loginForm";
    }

    @PostMapping("/join")
    public  String join(User user){
        System.out.println(user);
        user.setRole("ROLE_USER");
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);

        return "redirect:/loginForm";
    }

    @GetMapping("/joinForm")
    public  String joinForm(){
        return "joinForm";
    }

    @GetMapping("/joinProc")
    public  String joinProc(){
        return "회원가입 완료됨!";
    }
}
