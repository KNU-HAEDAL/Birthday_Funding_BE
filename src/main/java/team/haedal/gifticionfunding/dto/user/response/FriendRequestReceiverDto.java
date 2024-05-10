package team.haedal.gifticionfunding.dto.user.response;

import java.time.LocalDate;
import lombok.Builder;
import team.haedal.gifticionfunding.entity.user.FriendRequest;

@Builder
public record FriendRequestReceiverDto (
    Long id,
    UserInfoDto requestee,
    LocalDate createdAt
){
    public static FriendRequestReceiverDto from(FriendRequest action) {

        var user = action.getRequestee();
        return FriendRequestReceiverDto.builder()
            .id(action.getId())
            .requestee(UserInfoDto.from(user))
            .createdAt(action.getCreatedAt().toLocalDate())
            .build();
    }

}
