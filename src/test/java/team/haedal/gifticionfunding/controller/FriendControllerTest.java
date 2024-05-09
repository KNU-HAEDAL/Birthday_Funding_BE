package team.haedal.gifticionfunding.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import team.haedal.gifticionfunding.dto.response.GifticonResponse;
import team.haedal.gifticionfunding.service.FriendService;
import team.haedal.gifticionfunding.service.GifticonServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FriendController.class)
@AutoConfigureMockMvc
class FriendControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    FriendService friendService;

    @Autowired
    ObjectMapper objectMapper;

    //TODO: Test: 승인된 친구만 조회되는지(승인된 친구가 요청목록에 없어지는지), 친구 중복 요청안되는지
    //TODO: Test: 만약에 두명이 서로 친구 요청을 보내고 하나의 요청이 승인되면 -> 둘은 친구임 -> 근데 waiting인게 디비에 영원히 남아있음
    //TODO: Test: 친구가 서로 동시에 요청을 보낸다면?

    @Test
    @DisplayName("친구목록 조회 테스트")
    void getFriends() {

    }

    @Test
    @DisplayName("친구 요청 테스트")
    void requestFriend() {
    }

    @Test
    @DisplayName("친구 수락 테스트")
    void acceptFriend() {
    }

    @Test
    @DisplayName("친구 거절 테스트")
    void rejectFriend() {
    }

    @Test
    @DisplayName("요청된 친구 목록 조회 테스트")
    void getRequestedFriends() {
    }
}