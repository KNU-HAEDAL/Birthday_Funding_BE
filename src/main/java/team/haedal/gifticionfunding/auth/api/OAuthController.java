package team.haedal.gifticionfunding.auth.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.haedal.gifticionfunding.auth.service.OAuthService;
import team.haedal.gifticionfunding.auth.service.SecurityService;
import team.haedal.gifticionfunding.service.user.UserService;

@RestController
@RequiredArgsConstructor
public class OAuthController {
    private final OAuthService oAuthService;
    private final SecurityService securityService;
    private final UserService userService;

    @GetMapping("/oauth2")
    public ResponseEntity<Void> socialLogin(@RequestParam("code") String code){
        String email= oAuthService.getEmail(code);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

}
