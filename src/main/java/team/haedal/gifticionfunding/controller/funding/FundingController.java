package team.haedal.gifticionfunding.controller.funding;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.haedal.gifticionfunding.dto.common.PagingRequest;
import team.haedal.gifticionfunding.dto.common.PagingResponse;
import team.haedal.gifticionfunding.dto.funding.request.FundingCreateRequest;
import team.haedal.gifticionfunding.dto.funding.response.FundingArticleResponse;
import team.haedal.gifticionfunding.service.funding.FundingService;

@Tag(name = "펀딩", description = "펀딩 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/funding")
public class FundingController {

    private final FundingService fundingService;

    @Operation(summary = "펀딩 게시글 생성", description = "펀딩 게시글 생성")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createFunding(@Valid @RequestBody FundingCreateRequest fundingCreateRequest,
        Principal principal) {
        log.info("펀딩 게시글 생성");
        return fundingService.createFunding(fundingCreateRequest.toDomain(),
            fundingCreateRequest.gifticonIds(),
            Long.parseLong(principal.getName()));
    }

    @Operation(summary = "펀딩 게시글 페이징 조회", description = "펀딩 게시글 페이징 조회")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PagingResponse<FundingArticleResponse> getFundings(@Valid PagingRequest pagingRequest
    ) {
        log.info("펀딩 게시글 페이징 조회");
        return fundingService.getFundingArticlePaging(pagingRequest);
    }

    @Operation(summary = "펀딩 게시글 상세 조회", description = "펀딩 게시글 상세 조회")
    @GetMapping("/{fundingId}")
    @ResponseStatus(HttpStatus.OK)
    public void getFunding() {
        log.info("펀딩 게시글 상세 조회");
    }

    @Operation(summary = "펀딩 게시글 수정", description = "펀딩 게시글 수정")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateFunding() {
        log.info("펀딩 게시글 수정");
    }

    @Operation(summary = "펀딩 게시글 삭제", description = "펀딩 게시글 삭제")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFunding() {
        log.info("펀딩 게시글 삭제");
    }


}
