package team.haedal.gifticionfunding.repository.gifticon;

import org.springframework.data.jpa.repository.JpaRepository;
import team.haedal.gifticionfunding.entity.gifticon.UserGifticon;
import team.haedal.gifticionfunding.entity.user.User;

import java.util.List;

public interface UserGifticonJpaRepository extends JpaRepository<UserGifticon, Long> {
    List<UserGifticon> findByOwner(User owner);

    List<UserGifticon> findByBuyer(User buyer);

}
