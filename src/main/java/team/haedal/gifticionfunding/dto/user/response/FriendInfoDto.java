package team.haedal.gifticionfunding.dto.user.response;

import java.time.LocalDate;
import lombok.Builder;
import team.haedal.gifticionfunding.entity.user.Friendship;

@Builder
public record FriendInfoDto(
    Long friendshipId,
    Long friendId,
    String nickname,
    String profileImageUrl,
    LocalDate birthDate) {

    public static FriendInfoDto from(Friendship friendship) {
        var friend = friendship.getUser2();
        return FriendInfoDto.builder()
            .friendshipId(friendship.getId())
            .friendId(friend.getId())
            .nickname(friend.getNickname())
            .profileImageUrl(friend.getProfileImageUrl())
            .birthDate(friend.getBirthdate())
            .build();
    }


}
