package team.haedal.gifticionfunding.service.gifticon;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.haedal.gifticionfunding.entity.gifticon.Gifticon;
import team.haedal.gifticionfunding.entity.gifticon.GifticonCreate;
import team.haedal.gifticionfunding.entity.gifticon.GifticonUpdate;
import team.haedal.gifticionfunding.global.validation.GifticonUpdateValidator;
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
    public Long registerGifticon(GifticonCreate gifticonCreate){
        Gifticon gifticon = Gifticon.createGifticon(gifticonCreate);
        gifticonJpaRepository.save(gifticon);
        return gifticon.getId();
    }

    //상품 전체 조회
    public List<Gifticon> findGifticonAll(){
        return gifticonJpaRepository.findAll();
    }

    //상품 상세 조회
    public Gifticon findGifticon(Long gifticonId){
        Gifticon findGifticon= gifticonJpaRepository.findById(gifticonId).orElse(null);
        if(findGifticon == null){
            throw new IllegalArgumentException("해당 상품이 존재하지 않습니다.");
        }
        return findGifticon;
    }

    //상품 수정
    @Transactional
    public Gifticon updateGifticon(Long gifticonId, GifticonUpdate gifticonUpadate){
        Gifticon findGifticon = gifticonJpaRepository.findById(gifticonId).orElse(null);
        if(findGifticon == null){
            throw new IllegalArgumentException("해당 상품이 존재하지 않습니다.");
        }
        //gifticonUpdate 검증
        GifticonUpdateValidator.validate(gifticonUpadate);

        return findGifticon.updateGifticon(gifticonUpadate);
    }

    //상품 삭제
    @Transactional
    public void deleteGifticon(Long gifticonId){
        gifticonJpaRepository.deleteById(gifticonId);
    }

    /**
     * 재고 추가
     */
    @Transactional
    public void addStock(Long gifticonId, Long stock){
        Gifticon findGifticon = gifticonJpaRepository.findById(gifticonId).orElse(null);
        if(findGifticon == null){
            throw new IllegalArgumentException("해당 상품이 존재하지 않습니다.");
        }
        findGifticon.addStock(stock);
    }

    /**
     * 재고 감소
     */
    @Transactional
    public void removeStock(Long gifticonId, Long stock){
        Gifticon findGifticon = gifticonJpaRepository.findById(gifticonId).orElse(null);
        if(findGifticon == null){
            throw new IllegalArgumentException("해당 상품이 존재하지 않습니다.");
        }
        findGifticon.removeStock(stock);
    }


}
