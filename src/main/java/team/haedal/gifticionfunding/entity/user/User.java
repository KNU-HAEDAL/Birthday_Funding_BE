package team.haedal.gifticionfunding.entity.user;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private Long point;

    @Column(nullable = false)
    private LocalDate birthdate;
    private String profileImageUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

}
