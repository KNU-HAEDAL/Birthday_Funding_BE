package team.haedal.gifticionfunding.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.haedal.gifticionfunding.dto.gifticon.request.GifticonCreateRequest;
import team.haedal.gifticionfunding.dto.gifticon.request.GifticonUpdateRequest;
import team.haedal.gifticionfunding.dto.gifticon.response.GifticonDetailResponse;
import team.haedal.gifticionfunding.dto.gifticon.response.GifticonResponse;
import team.haedal.gifticionfunding.entity.gifticon.Gifticon;
import team.haedal.gifticionfunding.entity.gifticon.GifticonCreate;
import team.haedal.gifticionfunding.service.gifticon.GifticonService;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gifticons")
public class GifticonController {
    private final GifticonService gifticonService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GifticonResponse>> getGifticons() {
        log.info("상품 전체조회");
        List<Gifticon> gifticons = gifticonService.findGifticonAll();
        return ResponseEntity.ok(GifticonResponse.fromList(gifticons));
    }

    @GetMapping("/{gifticonId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GifticonDetailResponse> getGifticon(@PathVariable("gifticonId") Long gifticonId) {
        log.info("gifticonId : {} 상품조회", gifticonId);
        Gifticon gifticon = gifticonService.findGifticon(gifticonId);
        return ResponseEntity.ok(GifticonDetailResponse.from(gifticon));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createGifticon(@RequestBody @Valid GifticonCreateRequest request) {
        log.info("상품 생성 name: {},price:{}",request.getName(), request.getPrice());
        final Long id = gifticonService.registerGifticon(request.toCommand());
        return ResponseEntity.created(URI.create("/api/gifticons/"+id)).build();
    }

    @PutMapping("/{gifticonId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<GifticonDetailResponse> putGifticon(@PathVariable("gifticonId") Long gifticonId, @RequestBody GifticonUpdateRequest request) {
        log.info("gifticonId : {} 상품 수정", gifticonId);
        Gifticon gifticon=gifticonService.updateGifticon(gifticonId, request.toCommand());
        return ResponseEntity.ok(GifticonDetailResponse.from(gifticon));
    }

    @DeleteMapping("/{gifticonId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteGifticon(@PathVariable("gifticonId") Long gifticonId) {
        log.info("gifticonId : {} 상품 삭제", gifticonId);
        gifticonService.deleteGifticon(gifticonId);
        return ResponseEntity.noContent().build();
    }


}
