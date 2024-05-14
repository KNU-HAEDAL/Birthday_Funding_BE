package team.haedal.gifticionfunding.repository.funding;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import team.haedal.gifticionfunding.entity.funding.FundingArticle;
import team.haedal.gifticionfunding.entity.gifticon.Gifticon;
import team.haedal.gifticionfunding.entity.user.QFriendship;

import java.util.List;
import java.util.function.Predicate;

import static team.haedal.gifticionfunding.entity.funding.QFundingArticle.fundingArticle;
import static team.haedal.gifticionfunding.entity.funding.QFundingArticleGifticon.fundingArticleGifticon;
import static team.haedal.gifticionfunding.entity.funding.QFundingContribute.*;
import static team.haedal.gifticionfunding.entity.gifticon.QGifticon.*;
import static team.haedal.gifticionfunding.entity.gifticon.QUserGifticon.*;

@Repository
@RequiredArgsConstructor
public class FundingQueryRepository {
    private final JPAQueryFactory queryFactory;

    /**
     * 친구와 나의 펀딩을 조회합니다. <br>
     * 성능에서 문제가 있을수 있음
     */
    public Page<FundingArticle> getFundingPage(PageRequest pageRequest, Long userId) {
        // 친구의 펀딩을 조회하기 위한 조건
        BooleanExpression friendExpression = fundingArticle.user.id.in(
            JPAExpressions.select(QFriendship.friendship.friend.id)
                .from(QFriendship.friendship)
                .where(
                    QFriendship.friendship.user.id.eq(userId)
                )
        );

        Long count = queryFactory.from(fundingArticle)
                .select(fundingArticle.count())
                .where(
                    friendExpression.or(fundingArticle.user.id.eq(userId))
                )
                .fetchOne();
        System.out.println(count);
        List<FundingArticle> fundingArticles = queryFactory.from(fundingArticle)
                .select(fundingArticle)
                .join(fundingArticle.user).fetchJoin()
                .where(
                    friendExpression.or(fundingArticle.user.id.eq(userId))
                )
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();
        return new PageImpl<>(fundingArticles, pageRequest, count == null ? 0 : count);

    }


    /**
     * 펀딩 아이디로 펀딩을 조회합니다. <br>
     * OneToMany 관계인 FundingArticleGifticon과 FundingArticleGifticon의 Gifticon을 fetchJoin합니다.
     * quesydsl에서 에러가 계속 떠서 짜기 힘들었다...
     */
    public FundingArticle findByIdWithUserOrThrow(Long fundingId) {
        FundingArticle article = queryFactory
                .select(fundingArticle)
                .from(fundingArticle)
                .join(fundingArticle.user).fetchJoin()
                .join(fundingArticle.fundingArticleGifticons, fundingArticleGifticon).fetchJoin()
                .join(fundingArticleGifticon.gifticon).fetchJoin()
                .where(fundingArticle.id.eq(fundingId))
                .fetchOne();


        if(article == null) {
            throw new IllegalArgumentException("해당 펀딩이 존재하지 않습니다.");
        }
        return article;
    }

    /**
     * 펀딩에 현재까지 모금된 금액을 조회합니다.
     * 이거도 짜기 힘들었음 레전드
     */
    public Integer getFundingArticleCurrentMoney(Long fundingId) {
        Integer i =queryFactory.select(fundingContribute.userGifticon.gifticon.price.sum())
            .from(fundingContribute)
            .join(fundingContribute.userGifticon, userGifticon)
            .join(userGifticon.gifticon)
            .where(fundingContribute.fundingArticle.id.eq(fundingId))
            .fetchOne();
        return i == null ? 0 : i;
    }
}
