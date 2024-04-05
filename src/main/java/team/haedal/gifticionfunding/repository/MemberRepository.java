package team.haedal.gifticionfunding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.haedal.gifticionfunding.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByNickname(String nickname);
}
