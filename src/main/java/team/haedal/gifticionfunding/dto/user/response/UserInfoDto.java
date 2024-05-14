package team.haedal.gifticionfunding.dto.user.response;

import java.time.LocalDate;
import lombok.Builder;
import org.springframework.cglib.core.Local;
import team.haedal.gifticionfunding.entity.user.User;

@Builder
public record UserInfoDto(
    Long id,
    String nickname,
    String profileImageUrl,
    LocalDate birthDate
) {
    public static UserInfoDto from(User user) {
        return UserInfoDto.builder()
            .id(user.getId())
            .nickname(user.getNickname())
            .profileImageUrl(user.getProfileImageUrl())
            .birthDate(user.getBirthdate())
            .build();
    }

}
