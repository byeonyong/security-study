package com.by.securitystudy.config.auth;

// 시큐리티가 /login주소요청이 오면 낚아채서 로그인을 진행시켜줌
// Security Session에 Authentication 객체가 있어야 하고 Autentication객체에는 UserDetails 타입의 객체가 들어가있어야함.
// 결국 UserDetails 값이 Session에 들어가서 session정보를 유지시켜주는거

import com.by.securitystudy.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private User user;

    public PrincipalDetails(User user){
    this.user = user;
    }

    // 해당 User의 권한을 리턴하는곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });

        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername(){
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // User 활성화 여부, 휴면계정 설정할때 사용
    @Override
    public boolean isEnabled() {
        return true;
    }
}

