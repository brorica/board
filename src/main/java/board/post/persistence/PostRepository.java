package board.post.persistence;

import board.post.domain.Post;
import board.post.presentation.response.PostListEntry;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByIdAndDeleteIsFalse(final Long id);

    @Query("SELECT new board.post.presentation.response.PostListEntry(p.id, p.title) "
        +  "FROM Post p "
        +  "WHERE p.id < :lastPostId "
        +  "ORDER BY p.id DESC")
    List<PostListEntry> findPostsPage10(@Param("lastPostId") final Long lastPostId, Pageable pageable);
}
