package team.haedal.gifticionfunding.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import team.haedal.gifticionfunding.dto.request.GifticonRequest;
import team.haedal.gifticionfunding.dto.response.GifticonResponse;
import team.haedal.gifticionfunding.dto.response.GifticonDetailResponse;
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
    public ResponseEntity<List<GifticonResponse>> getGifticons(){
        return ResponseEntity.ok().body(gifticonService.getGifticons());
    }
    @GetMapping("detail")
    public ResponseEntity<GifticonDetailResponse> getGifticonDetail(@RequestParam(name="id")Long id){
        return ResponseEntity.ok().body(gifticonService.getGifticonDetail(id));
    }
    @PostMapping("create")
    public ResponseEntity<Long> createGifticon(@RequestBody GifticonRequest request){
        return ResponseEntity.ok().body(gifticonService.createGifticon(request));
    }
    @PutMapping("update")
    public ResponseEntity<Long> updateGifticon(@RequestParam(name="id")Long id,
                                               @RequestBody GifticonRequest request){
        return ResponseEntity.ok().body(gifticonService.updateGifticon(id,request));
    }
    @DeleteMapping("delete")
    public ResponseEntity<Long> deleteGifticon(@RequestParam(name="id")Long id){
        return ResponseEntity.ok().body(gifticonService.deleteGifticon(id));
    }


}
