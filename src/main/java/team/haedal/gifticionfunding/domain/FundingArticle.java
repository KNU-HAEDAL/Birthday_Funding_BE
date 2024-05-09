package team.haedal.gifticionfunding.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class FundingArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funding_id")
    private Long id;
    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;
    private LocalDateTime createdAt;
    private LocalDate endAt;
    private String title;
    @Column(length = 50000)
    private String content;
    @ManyToMany
    @JoinTable(name = "funding_gifticon",
            joinColumns = @JoinColumn(name = "funding_id"),
            inverseJoinColumns = @JoinColumn(name = "gifticon_id"))
    private List<Gifticon> gifticonList = new ArrayList<>();

    @Builder
    private FundingArticle(Long id, Member member, LocalDateTime createdAt, LocalDate endAt, String title, String content, List<Gifticon> gifticonList){
        this.id = id;
        this.member = member;
        this.createdAt = createdAt;
        this.endAt = endAt;
        this.title = title;
        this.content = content;
        this.gifticonList = gifticonList;
    }


    public static FundingArticle of(Member member, LocalDate endAt, String title, String content, List<Gifticon> gifticonList){
        return FundingArticle.builder()
                .member(member)
                .createdAt(LocalDateTime.now())
                .endAt(endAt)
                .title(title)
                .content(content)
                .gifticonList(gifticonList)
                .build();
    }
}
