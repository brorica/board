package board.member.persistence;

import board.member.domain.Member;
import board.member.presentation.dto.LoginMemberInfo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m.id FROM Member m WHERE m.email = :email")
    List<Long> findIdByEmail(@Param("email") final String email);

    @Query("SELECT new board.member.presentation.dto.LoginMemberInfo(m.id, m.email) "
        +  "FROM Member m "
        +  "WHERE m.email = :email AND m.password = :password")
    List<LoginMemberInfo> findByEmailAndPassword(@Param("email") final String email,
                                                 @Param("password") final String password);

}
