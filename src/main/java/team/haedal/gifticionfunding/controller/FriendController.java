package team.haedal.gifticionfunding.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequiredArgsConstructor
@EnableWebMvc
@RequestMapping("/api/friend")
@Tag(name="Friend",description="친구 관련 API")
public class FriendController {
    @GetMapping()
    @Operation(summary = "친구 목록 조회 API")
    public ResponseEntity<?> getFriends(Authentication authentication){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/request")
    @Operation(summary = "친구 요청 API")
    public ResponseEntity<?> requestFriend(Authentication authentication,
                                           @RequestParam(name="friend") Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PostMapping("/accept")
    @Operation(summary = "친구 수락 API")
    public ResponseEntity<?> acceptFriend(Authentication authentication,
                                          @RequestParam(name="friend") Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PostMapping("/reject")
    @Operation(summary = "친구 거절 API")
    public ResponseEntity<?> rejectFriend(Authentication authentication,
                                          @RequestParam(name="friend") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/request")
    @Operation(summary = "친구 요청 조회 API")
    public ResponseEntity<?> getRequestedFriends(Authentication authentication){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


}
