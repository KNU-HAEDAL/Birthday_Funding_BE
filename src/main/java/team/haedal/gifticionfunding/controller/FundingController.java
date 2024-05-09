package team.haedal.gifticionfunding.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequiredArgsConstructor
@EnableWebMvc
@RequestMapping("/api/funding")
@Tag(name="Funding",description="펀딩 관련 API")
public class FundingController {
    //TODO: 가능한 펀딩 조회, 참여한 펀딩 조회, 생성한 펀딩 조회
    //TODO: 펀딩 생성 (유효하지 않은 기프티콘이 하나라도 포함된다면 전체 fail)
}
