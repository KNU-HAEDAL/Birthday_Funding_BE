package team.haedal.gifticionfunding.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "기프티콘 전체 조회", description = "기프티콘 정보를 모두 조회합니다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "기프티콘 전체 조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")

    })
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GifticonResponse>> getGifticons() {
        log.info("기프티콘 전체조회");
        List<Gifticon> gifticons = gifticonService.findGifticonAll();
        return ResponseEntity.ok(GifticonResponse.fromList(gifticons));
    }

    @Operation(summary = "기프티콘 조회", description = "기프티콘 정보를 조회합니다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "기프티콘 조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "404", description = "기프티콘을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/{gifticonId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GifticonDetailResponse> getGifticon(@PathVariable("gifticonId") Long gifticonId) {
        log.info("gifticonId : {} 기프티콘조회", gifticonId);
        Gifticon gifticon = gifticonService.findGifticon(gifticonId);
        return ResponseEntity.ok(GifticonDetailResponse.from(gifticon));
    }

    @Operation(summary = "기프티콘 생성", description = "기프티콘을 생성합니다")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "기프티콘 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createGifticon(@RequestBody @Valid GifticonCreateRequest request) {
        log.info("기프티콘 생성 name: {},price:{}",request.getName(), request.getPrice());
        final Long id = gifticonService.registerGifticon(request.toCommand());
        return ResponseEntity.created(URI.create("/api/gifticons/"+id)).build();
    }

    @Operation(summary = "기프티콘 수정", description = "기프티콘을 수정합니다")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "기프티콘 수정 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "404", description = "기프티콘을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PutMapping("/{gifticonId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<GifticonDetailResponse> putGifticon(@PathVariable("gifticonId") Long gifticonId, @RequestBody GifticonUpdateRequest request) {
        log.info("gifticonId : {} 기프티콘 수정", gifticonId);
        Gifticon gifticon=gifticonService.updateGifticon(gifticonId, request.toCommand());
        return ResponseEntity.ok(GifticonDetailResponse.from(gifticon));
    }

    @Operation(summary = "기프티콘 삭제", description = "기프티콘을 삭제합니다")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "기프티콘 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "404", description = "기프티콘을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @DeleteMapping("/{gifticonId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteGifticon(@PathVariable("gifticonId") Long gifticonId) {
        log.info("gifticonId : {} 기프티콘 삭제", gifticonId);
        gifticonService.deleteGifticon(gifticonId);
        return ResponseEntity.noContent().build();
    }


}
