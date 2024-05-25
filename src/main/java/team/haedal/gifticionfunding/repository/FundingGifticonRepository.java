package team.haedal.gifticionfunding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.haedal.gifticionfunding.domain.funding.FundingGifticon;

public interface FundingGifticonRepository extends JpaRepository<FundingGifticon, Long> {
}
