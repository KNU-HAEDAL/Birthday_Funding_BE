package team.haedal.gifticionfunding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.haedal.gifticionfunding.domain.Gifticon;

import java.util.List;

public interface GifticonRepository extends JpaRepository<Gifticon, Long> {
    List<Gifticon> findByIdIn(List<Long> id);
}
