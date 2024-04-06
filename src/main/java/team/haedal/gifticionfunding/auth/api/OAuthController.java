package team.haedal.gifticionfunding.auth.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.haedal.gifticionfunding.auth.dto.TokenDto;
import team.haedal.gifticionfunding.auth.service.OAuthService;
import team.haedal.gifticionfunding.auth.service.SecurityService;
import team.haedal.gifticionfunding.dto.user.request.UserCreate;
import team.haedal.gifticionfunding.entity.user.User;
import team.haedal.gifticionfunding.service.user.UserService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OAuthController {
    private final OAuthService oAuthService;
    private final SecurityService securityService;
    private final UserService userService;

    @GetMapping("/oauth2")
    public ResponseEntity<Void> socialLogin(@RequestParam("code") String code){
        UserCreate userInfo=oAuthService.getUserInfo(code);
        log.info("userInfo: {}",userInfo);
        Optional<User> user=userService.SignIn(userInfo);
        log.info("user: {}",user.get().getId());
        TokenDto tokenDto=securityService.generateTokenDto(user.get().getId());
        HttpHeaders headers=securityService.setTokenHeaders(tokenDto);
        log.info("로그인 성공");

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .build();
    }

}
