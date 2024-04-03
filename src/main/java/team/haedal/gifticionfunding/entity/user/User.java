package team.haedal.gifticionfunding.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // @NotNull 어노테이션을 쓰면, 데이터베이스에 SQL 쿼리를 보내기 전에 예외가 발생한다.
    // nullable=false 로 @Column 어노테이션에 속성을 붙이면,
    // null을 넣은 엔티티를 생성하면 생성이 된 뒤 Repository에 전달되고,
    // 이 값이 DB에 넘어간 뒤에 예외가 발생해 위험한 오류를 맞을 수 있다.
    @Column(unique = true)
    @NotNull
    private String email;

    @Column(unique = true)
    @NotNull
    private String nickname;

    @NotNull
    private Long point;

    @NotNull
    private LocalDate birthdate;
    private String profileImageUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * 생성 메서드
     */
    public static User createUser(String email, String nickname, Long point, LocalDate birthdate, String profileImageUrl, Role role) {
        User user = new User();
        user.email = email;
        user.nickname = nickname;
        user.point = point;
        user.birthdate = birthdate;
        user.profileImageUrl = profileImageUrl;
        user.role = role;
        return user;
    }

    /**
     * 빌더 패턴
     */
    @Builder
    private User(String email, String nickname, Long point, LocalDate birthdate, String profileImageUrl, Role role) {
        this.email = email;
        this.nickname = nickname;
        this.point = point;
        this.birthdate = birthdate;
        this.profileImageUrl = profileImageUrl;
        this.role = role;
    }

}
