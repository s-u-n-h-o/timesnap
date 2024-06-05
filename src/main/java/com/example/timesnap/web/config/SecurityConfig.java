package com.example.timesnap.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //모든 요청 URL이 스프링 시큐리티를 제어받도록 만드는 어노테이션으로 내부적으로 SecurityFilterChain이 동작한다.
@EnableGlobalAuthentication
public class SecurityConfig {

    //SecurityFilterChain을 통해서 스프링시큐리티 필터를 관리할수 있다.
    //HttpSecurity : Spring Security의 각종 설정시 사용한다 , 리소스(url)접근 권한을 설정한다.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //form메서드를 호출하여 form 로그인을 호출한다.
        http.csrf(c->c.disable()
                ).formLogin(formLogin -> formLogin
                    .loginPage("/loginForm") //기본적으로 SpringSecurity가 /login 이라는 url을 제공하는데 이것을 커스터마이징할수있도록함
                    .permitAll()
                    .loginProcessingUrl("/login") //사용자가 로그인 폼을 제출할때 url로 요청이 전송되며, Spring Security가 낚아채서 인증진행
                    .defaultSuccessUrl("/") //인증이 성공한후 리디렉션을 할 url을 지정할수있다
                ).authorizeHttpRequests(request -> request.requestMatchers("/user/**").authenticated() //user로 들어왔을때 접근가능
                    .requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN") //관리자는 관리자 역할을 부여받은 사람만 접근가능
                    .anyRequest().permitAll());

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
