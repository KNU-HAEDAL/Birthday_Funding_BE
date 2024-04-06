package team.haedal.gifticionfunding.service;

import team.haedal.gifticionfunding.dto.request.GifticonRequest;
import team.haedal.gifticionfunding.dto.response.GifticonResponse;
import team.haedal.gifticionfunding.dto.response.GifticonDetailResponse;

import java.util.List;

public interface GifticonService {
    List<GifticonResponse> getGifticons();
    GifticonDetailResponse getGifticonDetail(Long id);
    Long deleteGifticon(Long id);
    Long createGifticon(GifticonRequest request);
    Long updateGifticon(Long id,GifticonRequest request);
}
