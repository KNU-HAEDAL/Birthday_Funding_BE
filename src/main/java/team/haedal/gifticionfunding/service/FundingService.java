package team.haedal.gifticionfunding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.haedal.gifticionfunding.domain.*;
import team.haedal.gifticionfunding.domain.funding.FundingArticle;
import team.haedal.gifticionfunding.dto.PageResponse;
import team.haedal.gifticionfunding.dto.request.FundingArticleRequest;
import team.haedal.gifticionfunding.dto.request.FundingJoinRequest;
import team.haedal.gifticionfunding.dto.response.FundingResponse;
import team.haedal.gifticionfunding.exception.CustomException;
import team.haedal.gifticionfunding.exception.ErrorCode;
import team.haedal.gifticionfunding.repository.*;

import java.util.List;
import java.util.stream.Collectors;

import static team.haedal.gifticionfunding.common.DateUtils.diff;

@Service
@RequiredArgsConstructor
public class FundingService {
    private final MemberRepository memberRepository;
    private final FundingArticleRepository fundingArticleRepository;
    private final GifticonRepository gifticonRepository;
    private final FundingContributeRepository contributeRepository;
    private final MemberGifticonRepository memberGifticonRepository;
    private final FundingGifticonRepository fundingGifticonRepository;

    @Transactional(readOnly = true)
    public PageResponse<FundingResponse> getFundings(int page, String name){
        /**
         * funding article 의 작성자가 친구인 경우, 친구의 친구인 경우 조회
         */
        Member owner = memberRepository.getUserByName(name);
        Page<FundingArticle> pageData = fundingArticleRepository.findByFriendAndFriendOfFriend(PageRequest.of(page, 10), owner.getId());

        return PageResponse.<FundingResponse>builder()
                .content(pageData.getContent().stream()
                        .map(FundingResponse::from)
                        .collect(Collectors.toList()))
                .page(pageData.getNumber())
                .isLast(pageData.isLast())
                .build();
    }

    @Transactional(readOnly = true)
    public PageResponse<FundingResponse> getTakenFundings(int page, String name){
        return null;
    }

    public PageResponse<FundingResponse> getCreatedFundgins(int page, String name){
        Member owner = memberRepository.getUserByName(name);
        Page<FundingArticle> pageData = fundingArticleRepository.findByMember(PageRequest.of(page, 10), owner);

        return PageResponse.<FundingResponse>builder()
                .content(pageData.getContent().stream()
                        .map(FundingResponse::from)
                        .collect(Collectors.toList()))
                .page(pageData.getNumber())
                .isLast(pageData.isLast())
                .build();
    }

    /**
     * 1. member
     * 2. 마감일이 유효한지
     * 3. 기프티콘이 유효한지(재고가 남아있어야 함)
     */
    @Transactional
    public void createFunding(String name, FundingArticleRequest request){
        Member member = memberRepository.getUserByName(name);

        long betweenDate = diff(request.getEndAt(),member.getBirthdate());
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


    /**
     * 게시자의 친구의 친구는 펀딩에 참여할 수 있다.
     * 선택한 기프티콘이 게시자가 게시한 기프티콘 목록에 있다면 contribute를 한다.
     * @param name
     * @param request
     */
    @Transactional
    public void participateFunding(String name, FundingJoinRequest request){
        Member member = memberRepository.getUserByName(name);
        FundingArticle fundingArticle = fundingArticleRepository.findById(request.getArticleId())
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INPUT, "존재하지 않는 게시글입니다."));
        MemberGifticon gifticon = memberGifticonRepository.findById(request.getMemberGifticonId())
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INPUT, "존재하지 않는 기프티콘입니다."));

        if (!fundingArticle.getGifticonList().contains(request.getMemberGifticonId())){
            throw new CustomException(ErrorCode.INVALID_INPUT,"존재하지 않는 기프티콘입니다.");
        }


    }
}
