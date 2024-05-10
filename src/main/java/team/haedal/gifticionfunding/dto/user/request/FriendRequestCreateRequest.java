package team.haedal.gifticionfunding.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FriendRequestCreateRequest {
    @NotBlank(message = "요청자 아이디는 필수 입력 값입니다.")
    private final Long requesterId;
    @NotBlank(message = "수신자 아이디는 필수 입력 값입니다.")
    private final Long receiverId;

    @Builder
    private FriendRequestCreateRequest(Long requesterId, Long receiverId) {
        this.requesterId = requesterId;
        this.receiverId = receiverId;
    }

    public static FriendRequestCreateRequest from(Long requesterId, Long receiverId){
        return FriendRequestCreateRequest.builder()
                .requesterId(requesterId)
                .receiverId(receiverId)
                .build();
    }

}
