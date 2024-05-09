package team.haedal.gifticionfunding.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import team.haedal.gifticionfunding.dto.PageResponse;
import team.haedal.gifticionfunding.dto.response.friend.FriendResponse;
import team.haedal.gifticionfunding.service.FriendService;

@RestController
@RequiredArgsConstructor
@EnableWebMvc
@RequestMapping("/api/friend")
@Tag(name="Friend",description="친구 관련 API")
public class FriendController {
    //TODO: 친구 목록, 요청받은 친구 목록
    //TODO: 친구 요청, 요청 수락, 요청 거절, 요청 취소
    private final FriendService friendService;

    @GetMapping()
    @Operation(summary = "친구 목록 조회 API")
    public ResponseEntity<PageResponse<FriendResponse>> getFriends(Authentication authentication,
                                                                   @RequestParam(value="page", defaultValue="0") int page){
        return ResponseEntity.status(HttpStatus.OK).body(friendService.getFriends(page,authentication.getName()));
    }

    @PostMapping("/request")
    @Operation(summary = "친구 요청 API")
    public void requestFriend(Authentication authentication,
                                           @RequestParam(name="friend") Long id){
        System.out.println(authentication.getDetails().toString());
        friendService.requestFriend(authentication.getName(),id);
    }

    @PostMapping("/accept")
    @Operation(summary = "친구 수락 API")
    public void acceptFriend(Authentication authentication,
                                          @RequestParam(name="friend") Long id){
        friendService.acceptFriend(authentication.getName(),id);    }

    @PostMapping("/reject")
    @Operation(summary = "친구 거절 API")
    public void rejectFriend(Authentication authentication,
                                          @RequestParam(name="friend") Long id){
        friendService.rejectFriend(authentication.getName(),id);
    }

    @GetMapping("/request")
    @Operation(summary = "친구 요청 조회 API", description ="수락 대기중인 요청만 나타낸다.")
    public ResponseEntity<PageResponse<FriendResponse>> getRequestedFriends(Authentication authentication,
                                                                            @RequestParam(value="page", defaultValue="0") int page){
        return ResponseEntity.status(HttpStatus.OK).body(friendService.getRequestedFriends(page, authentication.getName()));
    }


}
