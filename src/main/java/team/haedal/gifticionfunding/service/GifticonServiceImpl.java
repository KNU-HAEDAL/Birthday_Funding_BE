package team.haedal.gifticionfunding.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.haedal.gifticionfunding.domain.Gifticon;
import team.haedal.gifticionfunding.dto.request.GifticonRequest;
import team.haedal.gifticionfunding.dto.response.GifticonResponse;
import team.haedal.gifticionfunding.dto.response.GifticonDetailResponse;
import team.haedal.gifticionfunding.exception.CustomException;
import team.haedal.gifticionfunding.exception.ErrorCode;
import team.haedal.gifticionfunding.repository.GifticonRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GifticonServiceImpl implements GifticonService{
    private final GifticonRepository gifticonRepository;
    @Override
    public List<GifticonResponse> getGifticons(){
        List<GifticonResponse> gifticonResponseList = new ArrayList<>();
        List<Gifticon> gifticons = gifticonRepository.findAll();
        for (Gifticon gifticon : gifticons)
            gifticonResponseList.add(GifticonResponse.from(gifticon));

        return gifticonResponseList;
    }

    @Override
    public GifticonDetailResponse getGifticonDetail(Long id) {
        Gifticon gifticon = gifticonRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        return GifticonDetailResponse.from(gifticon);
    }

    @Override
    @Transactional
    public Long deleteGifticon(Long id) {
        Gifticon gifticon = gifticonRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
        gifticonRepository.delete(gifticon);
        return id;
    }

    @Override
    @Transactional
    public Long createGifticon(GifticonRequest request) {
        Gifticon newGifticon = gifticonRepository.save(Gifticon.from(request));
        return newGifticon.getId();
    }

    @Override
    @Transactional
    public Long updateGifticon(Long id, GifticonRequest request) {
        Gifticon gifticon = gifticonRepository.findById(id)
                .orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND));
        gifticon.update(request);
        return gifticon.getId();
    }

}
