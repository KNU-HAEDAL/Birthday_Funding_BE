package team.haedal.gifticionfunding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.haedal.gifticionfunding.domain.Member;
import team.haedal.gifticionfunding.exception.CustomException;
import team.haedal.gifticionfunding.exception.ErrorCode;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByNickname(String nickname);

    default Member getUserByName(String name) {
        return this.findByNickname(name).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    default Member getUserById(Long id) {
        return this.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}
