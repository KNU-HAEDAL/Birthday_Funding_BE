package team.haedal.gifticionfunding.controller.gifticon;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import team.haedal.gifticionfunding.auth.jwt.JwtProvider;
import team.haedal.gifticionfunding.dto.gifticon.request.GifticonCreateRequest;
import team.haedal.gifticionfunding.entity.gifticon.Category;
import team.haedal.gifticionfunding.entity.gifticon.GifticonCreate;
import team.haedal.gifticionfunding.service.gifticon.GifticonService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GifticonController.class)
@AutoConfigureRestDocs(uriHost = "api.test.com", uriPort = 80)
public class GifticonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GifticonService gifticonService;

    @Test
    @DisplayName("[GET] [/api/gifticons] 기프티콘 조회 테스트")
    public void getGifticons() throws Exception {
        // given
        List<GifticonCreate> gifticonList = new ArrayList<>();
        gifticonList.add(GifticonCreate.builder()
                .price(1000L)
                .name("테스트")
                .category(Category.CULTURE)
                .stock(10L)
                .imageUrl("test")
                .description("test")
                .brand("test")
                .expirationPeriod(LocalDate.now())
                .build());

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/gifticons")
                       // .with(oauth2Login())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(gifticonList)))
                .andExpect(status().isOk())
                .andReturn();
    }
}
