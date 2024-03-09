package board.post.persistence;

import board.post.domain.Post;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByIdAndDeleteIsFalse(final Long id);

    Page<Post> findByDeleteFalseOrderByIdDesc(Pageable pageable);
}
