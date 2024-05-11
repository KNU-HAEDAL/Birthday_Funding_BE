package team.haedal.gifticionfunding.service.funding;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.haedal.gifticionfunding.dto.common.PagingRequest;
import team.haedal.gifticionfunding.dto.common.PagingResponse;
import team.haedal.gifticionfunding.dto.funding.domain.FundingArticleCreate;
import team.haedal.gifticionfunding.dto.funding.response.FundingArticleResponse;
import team.haedal.gifticionfunding.entity.funding.FundingArticle;
import team.haedal.gifticionfunding.entity.funding.FundingArticleGifticon;
import team.haedal.gifticionfunding.entity.gifticon.Gifticon;
import team.haedal.gifticionfunding.entity.user.User;
import team.haedal.gifticionfunding.repository.funding.FundingArticleGifticonJpaRepository;
import team.haedal.gifticionfunding.repository.funding.FundingArticleJpaRepository;
import team.haedal.gifticionfunding.repository.gifticon.GifticonJpaRepository;
import team.haedal.gifticionfunding.repository.user.UserJpaRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class FundingService {

    private final UserJpaRepository userJpaRepository;
    private final GifticonJpaRepository gifticonJpaRepository;
    private final FundingArticleJpaRepository fundingArticleJpaRepository;
    private final FundingArticleGifticonJpaRepository fundingArticleGifticonJpaRepository;

    /**
     * FundingArticle 및 FundingArticleGifticon 생성
     *
     * @return Funding id
     */
    public Long createFunding(FundingArticleCreate fundingArticleCreate, List<Long> gifticonIds,
        Long userId) {
        User user = userJpaRepository.findById(userId).orElseThrow();
        FundingArticle fundingArticle = FundingArticle.create(fundingArticleCreate, user);
        createFundingArticleGifticon(fundingArticle, gifticonIds);

        return fundingArticleJpaRepository.save(fundingArticle).getId();
    }

    /**
     * FundingArticleGifticon 생성
     *
     * @param fundingArticle
     * @param gifticonIds
     */
    private void createFundingArticleGifticon(FundingArticle fundingArticle,
        List<Long> gifticonIds) {
        List<Gifticon> gifticons = gifticonIds.stream()
            .map(gifticonId -> gifticonJpaRepository.findById(gifticonId).orElseThrow())
            .toList();

        List<FundingArticleGifticon> fundingArticleGifticons = gifticons.stream()
            .map(gifticon -> FundingArticleGifticon.create(fundingArticle, gifticon))
            .toList();

        fundingArticleGifticonJpaRepository.saveAll(fundingArticleGifticons);
    }

    /**
     * 펀딩 게시글 페이징 조회
     */
    public PagingResponse<FundingArticleResponse> getFundingArticlePaging(
        PagingRequest pagingRequest) {
        return PagingResponse.from(fundingArticleJpaRepository.findAll(pagingRequest.toPageable()),
            FundingArticleResponse::from);
    }
}
