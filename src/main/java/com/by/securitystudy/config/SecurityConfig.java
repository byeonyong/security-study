package com.by.securitystudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 시큐리티필터(아래 클래스내용)가 "스프링필터체인에 등록"됨
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 이렇게 써주면 해당 return 값을 IoC에 등록 할 수 있음.
    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/loginForm")
//                .usernameParameter("username2"0)
                .loginProcessingUrl("/login") // login주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행시켜줌
                .defaultSuccessUrl("/");
    }
}
