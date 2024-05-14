package team.haedal.gifticionfunding.service.gifticon;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import team.haedal.gifticionfunding.entity.gifticon.Category;
import team.haedal.gifticionfunding.entity.gifticon.GifticonUpdate;
import team.haedal.gifticionfunding.global.error.NotFoundGifticonException;

@DataJpaTest
@DisplayName("GifticonService 테스트")
@Import({GifticonService.class})
public class GifticonServiceTest {

    @Autowired
    private GifticonService gifticonService;

    @Test
    @DisplayName("존재하지 않은 id로 Gifticon 조회 시 NotFoundGifticonException 발생")
    void findGifticon_NotFoundGifticonException() {
        // given
        Long gifticonId = 1L;   // 존재하지 않는 id

        // when, then
        assertThatCode(() -> gifticonService.findGifticon(gifticonId))
            .isInstanceOf(NotFoundGifticonException.class)
            .hasMessage("해당 상품이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("존재하지 않은 id로 Gifticon 수정 시 NotFoundGifticonException 발생")
    void updateGifticon_NotFoundGifticonException() {
        // given
        Long gifticonId = 1L;   // 존재하지 않는 id
        // gifticonUpdate 객체 생성
        GifticonUpdate gifticonUpdate = GifticonUpdate.builder()
            .price(10000L)
            .name("테스트 상품")
            .category(Category.BEAUTY)
            .stock(100L)
            .imageUrl("test.com")
            .description("테스트 상품입니다.")
            .brand("테스트 브랜드")
            .expirationPeriod(LocalDate.now().plusDays(30))
            .build();

        // when, then
        assertThatCode(() -> gifticonService.updateGifticon(gifticonId, gifticonUpdate))
            .isInstanceOf(NotFoundGifticonException.class)
            .hasMessage("해당 상품이 존재하지 않습니다.");
    }


}
