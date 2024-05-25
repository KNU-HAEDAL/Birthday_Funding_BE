package team.haedal.gifticionfunding.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team.haedal.gifticionfunding.domain.funding.FundingArticle;
import team.haedal.gifticionfunding.domain.Member;

public interface FundingArticleRepository extends JpaRepository<FundingArticle, Long> {
    Page<FundingArticle> findByMember(Pageable pageable, Member member);

    @Query(value = "select * from funding_article where member_id in " +
            "((select distinct b.friend_id " +
            "from friendship a join friendship b " +
            "where a.member_id = :memberId and b.friend_id != :memberId " +
            "and a.friend_id=b.member_id) " +
            "union " +
            "(select a.friend_id " +
            "from friendship a where a.member_id = :memberId))",nativeQuery = true)
    Page<FundingArticle> findByFriendAndFriendOfFriend(Pageable pageable, @Param("memberId") Long memberId);
}
