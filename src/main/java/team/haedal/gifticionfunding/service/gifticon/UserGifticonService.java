package team.haedal.gifticionfunding.service.gifticon;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.haedal.gifticionfunding.entity.gifticon.GifticonPurchase;
import team.haedal.gifticionfunding.entity.gifticon.UserGifticon;
import team.haedal.gifticionfunding.entity.user.User;
import team.haedal.gifticionfunding.repository.gifticon.UserGifticonJpaRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserGifticonService {
    private final UserGifticonJpaRepository userGifticonJpaRepository;

    /**
     * 유저기프티콘 전체 조회
     */
    public List<UserGifticon> findUserGifticonAll()
    {
        return userGifticonJpaRepository.findAll();
    }

    /**
     * 유저기프티콘 상세 조회
     */
    public UserGifticon findUserGifticon(Long userGifticonId)
    {
        return userGifticonJpaRepository.findById(userGifticonId).orElse(null);
    }

    /**
     * 유저기프티콘 소유자별 조회
     */
    public List<UserGifticon> findUserGifticonByOwner(User owner)
    {
        return userGifticonJpaRepository.findByOwner(owner);
    }

    /**
     * 유저기프티콘 구매자별 조회
     */
    public List<UserGifticon> findUserGifticonByBuyer(User buyer)
    {
        return userGifticonJpaRepository.findByBuyer(buyer);
    }

    /**
     * 유저기프티콘 삭제
     */
    @Transactional
    public void deleteUserGifticon(Long userGifticonId)
    {
        userGifticonJpaRepository.deleteById(userGifticonId);
    }




    /**
     * 기프티콘 구매
     */
    @Transactional
    public Long purchaseGifticon(GifticonPurchase gifticonPurchase)
    {
        UserGifticon userGifticon = UserGifticon.purchaseGifticon(gifticonPurchase);
        userGifticonJpaRepository.save(userGifticon);
        return userGifticon.getId();
    }

    /**
     * 기프티콘 사용
     */
    @Transactional
    public void useGifticon(Long userGifticonId)
    {
        UserGifticon userGifticon = userGifticonJpaRepository.findById(userGifticonId).orElse(null);
        if(userGifticon == null)
        {
            throw new IllegalArgumentException("해당 기프티콘이 존재하지 않습니다.");
        }
        userGifticon.useGifticon();
    }

    /**
     * 기프티콘 환불
     */
    @Transactional
    public void refundGifticon(Long userGifticonId)
    {
        UserGifticon userGifticon = userGifticonJpaRepository.findById(userGifticonId).orElse(null);
        if(userGifticon == null)
        {
            throw new IllegalArgumentException("해당 기프티콘이 존재하지 않습니다.");
        }
        userGifticon.refundGifticon();
    }

    /**
     * 기프티콘 소유자 변경
     */
    @Transactional
    public void changeOwner(Long userGifticonId, User owner)
    {
        UserGifticon userGifticon = userGifticonJpaRepository.findById(userGifticonId).orElse(null);
        if(userGifticon == null)
        {
            throw new IllegalArgumentException("해당 기프티콘이 존재하지 않습니다.");
        }
        userGifticon.changeOwner(owner);
    }


}
