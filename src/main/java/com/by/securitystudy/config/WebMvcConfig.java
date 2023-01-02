package com.by.securitystudy.config;

import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

        // mustache의 view resolver를 재설정할수가 있음 (mustacheViewResolver)
        // 원래는 ".mustache" 파일을 찾지만 ".html"파일을 찾을 수 있게 변경하는거
        MustacheViewResolver resolver = new MustacheViewResolver();

        resolver.setCharset("UTF-8");
        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html");

        registry.viewResolver(resolver);
    }
}