package team.haedal.gifticionfunding.service;

import lombok.Builder;
import lombok.Getter;
import team.haedal.gifticionfunding.domain.Member;
import team.haedal.gifticionfunding.domain.enums.Role;

import java.time.LocalDate;
import java.util.Map;

@Getter
public class OAuthAttributes {
    private final Map<String, String> attributes;
    private final String nickname;
    private final String profileImageUrl;

    @Builder
    public OAuthAttributes(Map<String,String> attributes, String nickname, String profileImageUrl) {
        this.attributes = attributes;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;

    }

    public static OAuthAttributes of(Map<String, String> attributes) {
        return OAuthAttributes.builder()
                .nickname(attributes.get("nickname"))
                .profileImageUrl(attributes.get("profile_image"))
                .attributes(attributes)
                .build();
    }

    public Member toMember(){
        return Member.builder()
                .email("example@kakao.com")
                .profileImageUrl(profileImageUrl)
                .nickname(nickname)
                .point(0L)
                .birthdate(LocalDate.of(2001,9,15))
                .role(Role.MEMBER)
                .build();
    }
}
