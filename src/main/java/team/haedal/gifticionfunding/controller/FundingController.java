package team.haedal.gifticionfunding.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import team.haedal.gifticionfunding.dto.PageResponse;
import team.haedal.gifticionfunding.dto.request.FundingArticleRequest;
import team.haedal.gifticionfunding.dto.response.FundingResponse;
import team.haedal.gifticionfunding.service.FundingService;

@RestController
@RequiredArgsConstructor
@EnableWebMvc
@RequestMapping("/api/funding")
@Tag(name="Funding",description="펀딩 관련 API")
public class FundingController {
    //TODO: 가능한 펀딩 조회, 참여한 펀딩 조회, 생성한 펀딩 조회
    //TODO: 펀딩 생성 (유효하지 않은 기프티콘이 하나라도 포함된다면 전체 fail)
    private final FundingService fundingService;

    @GetMapping()
    @Operation(summary = "가능한 펀딩 조회 API")
    public ResponseEntity<PageResponse<FundingResponse>> getFundings(Authentication authentication){
        return ResponseEntity.status(HttpStatus.OK).body(fundingService.getFundings(authentication.getName()));
    }

    @GetMapping("/participate")
    @Operation(summary = "참여한 펀딩 조회 API")
    public ResponseEntity<PageResponse<FundingResponse>> getTakenFundings(Authentication authentication){
        return ResponseEntity.status(HttpStatus.OK).body(fundingService.getTakenFundings(authentication.getName()));
    }

    @GetMapping("/create")
    @Operation(summary = "생성한 펀딩 조회 API")
    public ResponseEntity<PageResponse<FundingResponse>> getCreatedFundgins(Authentication authentication){
        return ResponseEntity.status(HttpStatus.OK).body(fundingService.getCreatedFundgins(authentication.getName()));
    }

    @PostMapping("/create")
    @Operation(summary = "펀딩 생성 API")
    public void createFunding(Authentication authentication,
                              @RequestBody FundingArticleRequest request){
        fundingService.createFunding(authentication.getName(), request);
    }

}
