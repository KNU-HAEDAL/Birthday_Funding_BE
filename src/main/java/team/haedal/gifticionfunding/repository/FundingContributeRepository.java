package team.haedal.gifticionfunding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.haedal.gifticionfunding.domain.funding.FundingContribute;

public interface FundingContributeRepository extends JpaRepository<FundingContribute, Long> {
}
