package team.haedal.gifticionfunding.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.haedal.gifticionfunding.dto.common.PagingRequest;
import team.haedal.gifticionfunding.dto.common.PagingResponse;
import team.haedal.gifticionfunding.dto.user.request.FriendRequestCreateRequest;
import team.haedal.gifticionfunding.dto.user.response.FriendInfoDto;
import team.haedal.gifticionfunding.dto.user.response.FriendRequestReceiverDto;
import team.haedal.gifticionfunding.dto.user.response.FriendRequestSenderDto;
import team.haedal.gifticionfunding.service.user.FriendService;

@Tag(name = "친구", description = "친구 API")
@Slf4j
@RequiredArgsConstructor
@RestController
public class FriendController {

    private final FriendService friendService;

    @Operation(summary = "친구 추가 요청", description = "친구 추가 요청 id 반환")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("api/user/friend")
    public void requestFriend(@RequestBody FriendRequestCreateRequest friendRequestCreateRequest) {
        log.info("친구 추가 요청");
        friendService.requestFriend(friendRequestCreateRequest.getRequesterId(),
            friendRequestCreateRequest.getReceiverId());
    }

    @Operation(summary = "친구 목록 조회", description = "목록 페이징 조회")
    @GetMapping("api/user/friend")
    public PagingResponse<FriendInfoDto> getFriendList(@Valid PagingRequest pagingRequest,
        Principal principal) {
        log.info("친구 목록 조회");
        return friendService.getFriendPaging(pagingRequest, Long.parseLong(principal.getName()));
    }

    @Operation(summary = "친구 추가 요청 보낸 목록 조회", description = "친구 추가 요청 목록 페이징 조회")
    @GetMapping("api/user/friend/request")
    public PagingResponse<FriendRequestReceiverDto> getFriendRequestList(
        @Valid PagingRequest pagingRequest, Principal principal) {
        log.info("친구 추가 요청 보낸 목록 조회");
        return friendService.getFriendRequestSenderPaging(pagingRequest,
            Long.parseLong(principal.getName()));

    }

    @Operation(summary = "친구 추가 요청 받은 목록 조회", description = "친구 추가 요청 받은 목록 페이징 조회")
    @GetMapping("api/user/friend/receive")
    public PagingResponse<FriendRequestSenderDto> getFriendRequestReceiveList(
        @Valid PagingRequest pagingRequest, Principal principal) {
        log.info("친구 추가 요청 받은 목록 조회");
        return friendService.getFriendRequestReceiverPaging(pagingRequest,
            Long.parseLong(principal.getName()));
    }

    @Operation(summary = "친구 추가 요청 수락", description = "친구 추가 요청 수락")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("api/user/friend/accept/{friendRequestId}")
    public void acceptFriend(Principal principal, @PathVariable Long friendRequestId) {
        log.info("친구 추가 요청 수락");
        friendService.acceptFriendRequest(friendRequestId);
    }


    @Operation(summary = "친구 추가 요청 거절", description = "친구 추가 요청 거절")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("api/user/friend/reject/{friendRequestId}")
    public void rejectFriend(Principal principal, @PathVariable Long friendRequestId) {
        log.info("친구 추가 요청 거절");
        friendService.rejectFriendRequest(friendRequestId);
    }

    @Operation(summary = "친구 삭제", description = "친구 삭제")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("api/user/friend/{friendId}")
    public void deleteFriend(@PathVariable Long friendId, Principal principal) {
        friendService.deleteFriend(Long.parseLong(principal.getName()), friendId);
    }

    @Operation(summary = "친구의 친구 목록 조회", description = "친구의 친구 목록 조회")
    @GetMapping("api/user/friend/related")
    public PagingResponse<FriendInfoDto> getRelatedFriendList(@Valid PagingRequest pagingRequest,
        Principal principal) {
        log.info("친구의 친구 목록 조회");
        return friendService.getRelatedFriendPaging(pagingRequest,
            Long.parseLong(principal.getName()));
    }


}
