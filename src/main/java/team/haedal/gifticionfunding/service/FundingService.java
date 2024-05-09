package team.haedal.gifticionfunding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.haedal.gifticionfunding.common.DateUtils;
import team.haedal.gifticionfunding.domain.FundingArticle;
import team.haedal.gifticionfunding.domain.Gifticon;
import team.haedal.gifticionfunding.domain.Member;
import team.haedal.gifticionfunding.dto.PageResponse;
import team.haedal.gifticionfunding.dto.request.FundingArticleRequest;
import team.haedal.gifticionfunding.dto.response.FundingResponse;
import team.haedal.gifticionfunding.exception.CustomException;
import team.haedal.gifticionfunding.exception.ErrorCode;
import team.haedal.gifticionfunding.repository.FundingArticleRepository;
import team.haedal.gifticionfunding.repository.GifticonRepository;
import team.haedal.gifticionfunding.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FundingService {
    private final MemberRepository memberRepository;
    private final FundingArticleRepository fundingArticleRepository;
    private final GifticonRepository gifticonRepository;

    public PageResponse<FundingResponse> getFundings(String name){
        return null;
    }

    public PageResponse<FundingResponse> getTakenFundings(String name){
        return null;
    }

    public PageResponse<FundingResponse> getCreatedFundgins(String name){
        return null;
    }

    /**
     * 1. member
     * 2. 마감일이 유효한지
     * 3. 기프티콘이 유효한지(재고가 남아있어야 함)
     */
    public void createFunding(String name, FundingArticleRequest request){
        Member member = memberRepository.getUserByName(name);

        int betweenDate = DateUtils.minus(request.getEndAt(),member.getBirthdate());
        if (betweenDate > 14 || betweenDate < 0)
            throw new CustomException(ErrorCode.INVALID_INPUT, "잘못된 마감일입니다.");

        List<Gifticon> gifticonList = gifticonRepository.findByIdIn(request.getGifticonIdList());
        gifticonList.forEach(gifticon -> {
            if (gifticon.getStock() <= 0)
                throw new CustomException(ErrorCode.INVALID_INPUT, "유효하지 않은 기프티콘이 있습니다.");
        });
        fundingArticleRepository.save(FundingArticle.of(member, request.getEndAt().plusDays(1),
                                                        request.getTitle(), request.getContent(),
                                                        gifticonList));
    }
}
