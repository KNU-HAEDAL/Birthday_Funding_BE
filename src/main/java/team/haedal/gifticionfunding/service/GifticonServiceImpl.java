package team.haedal.gifticionfunding.service;

import org.springframework.stereotype.Service;
import team.haedal.gifticionfunding.dto.response.GiftiConResponse;
import team.haedal.gifticionfunding.dto.response.GifticonDetailResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GifticonServiceImpl implements GifticonService{
    public List<GiftiConResponse> getGifticons(){
        List<GiftiConResponse> giftiConResponseList = new ArrayList<>();
        return giftiConResponseList;
    }

    @Override
    public GifticonDetailResponse getGifticonDetail() {
        return new GifticonDetailResponse(
                1L,
                "카페라떼",
                "스타벅스",
                "http://image.png",
                LocalDate.of(2024,5,5),
                LocalDate.of(2025,5,5));
    }
}
