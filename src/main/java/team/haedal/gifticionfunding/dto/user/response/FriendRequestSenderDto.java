package team.haedal.gifticionfunding.dto.user.response;

import java.time.LocalDate;
import lombok.Builder;
import team.haedal.gifticionfunding.entity.user.FriendRequest;

@Builder
public record FriendRequestSenderDto (
    Long id,
    UserInfoDto requester,
    LocalDate createdAt
){
    public static FriendRequestSenderDto from(FriendRequest action) {

        var user = action.getRequester();
        return FriendRequestSenderDto.builder()
            .id(action.getId())
            .requester(UserInfoDto.from(user))
            .createdAt(action.getCreatedAt().toLocalDate())
            .build();
    }

}
