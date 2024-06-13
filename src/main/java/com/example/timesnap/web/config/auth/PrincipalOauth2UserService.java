package com.example.timesnap.web.config.auth;

import com.example.timesnap.domain.user.User;
import com.example.timesnap.domain.user.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oauth2User = super.loadUser(userRequest);
        //구글에서 로그인 정보 가져오기
        Optional<OAuth2User> Optionaloauth2User = Optional.ofNullable(super.loadUser(userRequest)); //구글 로그인한 회원정보 조회

        //회원정보가 없는경우
        if(!Optionaloauth2User.isPresent()) {
            throw new OAuth2AuthenticationException("구글 로그인 정보가 없습니다.");
        }
        //회원정보가 저장되어있는지 확인
        log.info("username이 존재하는지 확인 : {} ", oauth2User.getAttribute("name").toString());
        User user = userRepository.findByUsername(oauth2User.getAttribute("name"));

        if(user == null) {//회원정보 저장
           user = User.builder()
                    .username(oauth2User.getAttribute("name"))
                    .password("")
                    .email(oauth2User.getAttribute("email"))
                    .nickname(oauth2User.getAttribute("given_name"))
                    .isHidden(true)
                    .role("ROLE_USER")
                    .build();
            userRepository.save(user);
            log.info("회원가입 완료");
        }

        return new PrincipalDetails(user, oauth2User.getAttributes());
    }
}
