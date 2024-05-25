package team.haedal.gifticionfunding.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageResponse<T> {
    private List<T> content;
    private int page;
    private boolean isLast;

    @Builder
    private PageResponse(List<T> content, int page, boolean isLast){
        this.content = content;
        this.page = page;
        this.isLast = isLast;
    }

    public static PageResponse<Object> from(Page<Object> data){
        return PageResponse.builder()
                .content(data.getContent())
                .page(data.getNumber())
                .isLast(data.isLast())
                .build();
    }

}
