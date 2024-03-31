package board.member.persistence;

import board.member.domain.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m.id FROM Member m WHERE m.email = :email")
    List<Long> findIdByEmail(@Param("email") final String email);

    List<Member> findAllByEmailAndPassword(final String email, final String password);

    Optional<Member> findByEmail(final String email);
}
