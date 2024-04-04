package team.haedal.gifticionfunding.service.gifticon;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.haedal.gifticionfunding.entity.gifticon.Gifticon;
import team.haedal.gifticionfunding.entity.gifticon.GifticonUpdate;
import team.haedal.gifticionfunding.repository.gifticon.GifticonJpaRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GifticonService {

    private final GifticonJpaRepository gifticonJpaRepository;

    //상품등록
    @Transactional
    public Long registerGifticon(Gifticon gifticon){
        gifticonJpaRepository.save(gifticon);
        return gifticon.getId();
    }

    //상품 전체 조회
    public List<Gifticon> findGifticonAll(){
        return gifticonJpaRepository.findAll();
    }

    //상품 상세 조회
    public Gifticon findGifticon(Long gifticonId){
        return gifticonJpaRepository.findById(gifticonId).orElse(null);
    }

    //상품 수정

    @Transactional
    public void updateGifticon(Long gifticonId, GifticonUpdate gifticonUpadate){
        Gifticon findGifticon = gifticonJpaRepository.findById(gifticonId).orElse(null);
        if(findGifticon == null){
            throw new IllegalArgumentException("해당 상품이 존재하지 않습니다.");
        }
        findGifticon.updateGifticon(gifticonUpadate);
    }

    //상품 삭제
    @Transactional
    public void deleteGifticon(Long gifticonId){
        gifticonJpaRepository.deleteById(gifticonId);
    }

}
