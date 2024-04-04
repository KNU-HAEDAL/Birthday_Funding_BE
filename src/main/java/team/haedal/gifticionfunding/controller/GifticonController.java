package team.haedal.gifticionfunding.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.haedal.gifticionfunding.dto.gifticon.request.GifticonCreateRequest;
import team.haedal.gifticionfunding.dto.gifticon.request.GifticonUpdateRequest;
import team.haedal.gifticionfunding.entity.gifticon.Gifticon;
import team.haedal.gifticionfunding.entity.gifticon.GifticonCreate;
import team.haedal.gifticionfunding.service.gifticon.GifticonService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gifticons")
public class GifticonController {
    private final GifticonService gifticonService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Gifticon> getGifticons() {
        return gifticonService.findGifticonAll();
    }

    @GetMapping("/{gifticonId}")
    @ResponseStatus(HttpStatus.OK)
    public Gifticon getGifticon(@PathVariable("gifticonId") Long gifticonId){
        log.info("gifticonId : {}", gifticonId);
        return gifticonService.findGifticon(gifticonId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Long createGifticon(@RequestBody @Valid GifticonCreateRequest request) {
        return gifticonService.registerGifticon(request.toCommand());
    }

    @PutMapping("/{gifticonId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Gifticon putGifticon(@PathVariable("gifticonId") Long gifticonId, @RequestBody GifticonUpdateRequest request) {
        return gifticonService.updateGifticon(gifticonId, request.toCommand());
    }

    @DeleteMapping("/{gifticonId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGifticon(@PathVariable("gifticonId") Long gifticonId) {
        gifticonService.deleteGifticon(gifticonId);
    }


}
