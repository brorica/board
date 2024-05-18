package board.upvote;

import board.member.domain.Member;
import board.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UpvoteRepository extends JpaRepository<Upvote, Long> {

    Optional<Upvote> findByMemberAndPost(Member member, Post post);

    @Query("SELECT COUNT(u) FROM Upvote u WHERE u.post.id = :postId AND u.isUpvote = true")
    Long countByPost(@Param("postId") Long postId);
}
