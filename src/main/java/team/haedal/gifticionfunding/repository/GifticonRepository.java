package team.haedal.gifticionfunding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.haedal.gifticionfunding.domain.Gifticon;

public interface GifticonRepository extends JpaRepository<Gifticon, Long> {
}
