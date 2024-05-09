package team.haedal.gifticionfunding.dto.response.friend;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Getter;
import team.haedal.gifticionfunding.domain.Member;

import java.time.LocalDate;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
public class FriendResponse {
    private Long id;
    private String nickname;
    private String profileImageUrl;
    private LocalDate birthdate;

    @Builder
    private FriendResponse(Long id, String nickname, String profileImageUrl, LocalDate birthdate){
        this.id = id;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.birthdate = birthdate;
    }

    public static FriendResponse from(Member member){
        return FriendResponse.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .profileImageUrl(member.getProfileImageUrl())
                .birthdate(member.getBirthdate())
                .build();
    }
}
