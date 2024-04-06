package team.haedal.gifticionfunding.service;

import team.haedal.gifticionfunding.dto.response.GiftiConResponse;
import team.haedal.gifticionfunding.dto.response.GifticonDetailResponse;

import java.util.List;

public interface GifticonService {
    List<GiftiConResponse> getGifticons();
    GifticonDetailResponse getGifticonDetail();
}
