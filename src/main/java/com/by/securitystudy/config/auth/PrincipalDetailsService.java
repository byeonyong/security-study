package com.by.securitystudy.config.auth;

import com.by.securitystudy.model.User;
import com.by.securitystudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 loginProcessingUri("/login")
// login요청이 오면 자동으로 UserDetailService 타입으로 IoC되어있는 loadUserByUsername 함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username);
        if(userEntity != null){
//          시큐리티세션(Authentication(내부 UserDetails))
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}
