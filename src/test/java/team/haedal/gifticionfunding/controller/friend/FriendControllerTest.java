package team.haedal.gifticionfunding.controller.friend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import team.haedal.gifticionfunding.auth.jwt.JwtProvider;
import team.haedal.gifticionfunding.service.user.FriendService;

@SpringBootTest
@AutoConfigureMockMvc
public class FriendControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FriendService friendService;

    @Autowired
    private JwtProvider jwtProvider;

    @Test
    @DisplayName("친구 목록 조회 페이징")
    @WithMockUser(username = "test", roles = "USER")
    void getFriendRequestTest() throws Exception {
        // given
        String token = jwtProvider.generateAccessTokenForTest();

        // when

        // then
        mockMvc.perform(get("/api/user/friend")
                .header("Authorization", "Bearer " + token)
                .param("page", "1")
                .param("size", "10"))
            .andExpect(status().isOk());
    }
}
