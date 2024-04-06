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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import team.haedal.gifticionfunding.dto.response.GiftiConResponse;
import team.haedal.gifticionfunding.dto.response.GifticonDetailResponse;
import team.haedal.gifticionfunding.service.GifticonServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GifticonController.class)
@AutoConfigureMockMvc
public class GifticonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GifticonServiceImpl gifticonService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("기프티콘 조회 테스트")
    void getGifticons() throws Exception {
        //given
        //gifticonService에 대한 Mock test
        List<GiftiConResponse> giftiConResponseList = new ArrayList<>();
        giftiConResponseList.add(new GiftiConResponse(1L,"기프티콘 이름",10000,"http://example.jpg"));
        given(gifticonService.getGifticons()).willReturn(
                giftiConResponseList
        );

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/gifticon")
                        .with(oauth2Login())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }
    @Test
    @DisplayName("기프티콘 상세 조회 테스트")
    void getGifticonDetail() throws Exception {
        //given
        //gifticonService에 대한 Mock test
        given(gifticonService.getGifticonDetail()).willReturn(
                new GifticonDetailResponse(
                        1L,
                        "카페라떼",
                        "스타벅스",
                        "http://image.png",
                        LocalDate.of(2024,5,5),
                        LocalDate.of(2025,5,5))
        );

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/gifticon/detail")
                                .with(oauth2Login())
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("id", String.valueOf(1L)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.brand").exists())
                .andExpect(jsonPath("$.imageUrl").exists())
                .andExpect(jsonPath("$.dateOfUse").exists())
                .andExpect(jsonPath("$.expirationDate").exists())
                .andDo(print());

    }
}
