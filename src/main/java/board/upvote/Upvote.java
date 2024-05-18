package board.upvote;

import board.common.BaseTimeEntity;
import board.member.domain.Member;
import board.post.domain.Post;
import jakarta.persistence.*;

@Entity
public class Upvote extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private Boolean isUpvote;

    public Upvote(final Member member, final Post post) {
        this.member = member;
        this.post = post;
        this.isUpvote = true;
    }

    public void toggle() {
        this.isUpvote = !this.isUpvote;
    }

    public Long getId() {
        return id;
    }

    public Boolean getUpvote() {
        return isUpvote;
    }

    protected Upvote() {
    }
}
