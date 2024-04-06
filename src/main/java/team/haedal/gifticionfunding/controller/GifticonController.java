package team.haedal.gifticionfunding.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import team.haedal.gifticionfunding.dto.response.GiftiConResponse;
import team.haedal.gifticionfunding.service.GifticonService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@EnableWebMvc
@RequestMapping("/api/gifticon")
@Tag(name="Gifticon",description="기프티콘 관련 API")
public class GifticonController {
    private final GifticonService gifticonService;
    @GetMapping()
    public ResponseEntity<List<GiftiConResponse>> getGifticons(){
        return ResponseEntity.ok().body(gifticonService.getGifticons());
    }


}
