package team.haedal.gifticionfunding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.haedal.gifticionfunding.domain.FundingArticle;

public interface FundingArticleRepository extends JpaRepository<FundingArticle, Long> {
}
