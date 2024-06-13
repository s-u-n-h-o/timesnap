package com.example.timesnap.web.exception.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;

//로그인 실패시 처리하는 예외 핸들러
//SimpleUrlAuthenticationFailureHandler를 extends로 상속시 AuthenticationFailureHandler가 해당 인터페이스를 구현한 것이된다.
@Slf4j
public class LoginExceptionHandler extends SimpleUrlAuthenticationFailureHandler {

    String errorMessage;
    //설정된 경우 리디렉션 또는 전달을 수행하고 defaultFailureUrl, 그렇지 않으면 401 오류 코드를 반환합니다.
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("-------실패 핸들러 시작------");

        if (exception instanceof RuntimeException) {
            errorMessage = "아이디 또는 비밀번호를 잘못입력했습니다. 입력하신 내용을 다시 확인해주세요";
        }
        super.onAuthenticationFailure(request, response, exception);
    }
}
