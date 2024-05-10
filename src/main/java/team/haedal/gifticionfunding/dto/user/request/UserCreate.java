package team.haedal.gifticionfunding.dto.user.request;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Getter;
import team.haedal.gifticionfunding.entity.user.Role;

import java.time.LocalDate;

@Getter
public class UserCreate {

    private final String email;
    private final String nickname;
    private final Long point;
    private final LocalDate birthdate;
    private final String profileImageUrl;
    private final Role role;

    @Builder
    private UserCreate(String email, String nickname, Long point, LocalDate birthdate, String profileImageUrl, Role role) {
        this.email = email;
        this.nickname = nickname;
        this.point = point;
        this.birthdate = birthdate;
        this.profileImageUrl = profileImageUrl;
        this.role = role;
    }

    public static UserCreate from(JsonNode jsonNode){
        return UserCreate.builder()
                .email(jsonNode.get("kakao_account").get("email").asText())
                .nickname(jsonNode.get("properties").get("nickname").asText())
                .point(0L)
                .birthdate(LocalDate.parse("1999-01-01"))
                //.birthdate(LocalDate.parse(jsonNode.get("kakao_account").get("birthday").asText()))
                .role(Role.USER)
                .build();
    }

}
