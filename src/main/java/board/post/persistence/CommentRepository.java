package board.post.persistence;

import board.post.domain.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.post.id = :postId AND c.delete = false")
    List<Comment> findAllByPostId(@Param("postId") final Long postId);
}
