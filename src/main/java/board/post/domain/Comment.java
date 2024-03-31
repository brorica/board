package board.post.domain;

import board.common.BaseTimeEntity;
import board.member.domain.Member;
import board.post.presentation.request.CommentCreateRequest;
import board.post.presentation.request.CommentUpdateRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean delete;

    public Comment(final Member member, final Post post, final CommentCreateRequest commentCreateRequest) {
        this.member = member;
        this.post = post;
        this.content = commentCreateRequest.getContent();
        this.delete = false;
    }

    public void delete(final Long memberId) {
        validate(memberId);
        this.delete = true;
    }

    public void update(final Long memberId, final CommentUpdateRequest commentUpdateRequest) {
        validate(memberId);
        this.content = commentUpdateRequest.getContent();
    }

    private void validate(final Long memberId) {
        if (this.member.getId() != memberId) {
            throw new RuntimeException("작성자가 아닙니다.");
        }
        if (this.delete) {
            throw new RuntimeException("존재하지 않는 글입니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public Long getPostId() {
        return post.getId();
    }

    public String getCreator() {
        return member.getEmail();
    }

    public String getContent() {
        return content;
    }

    protected Comment() {
    }
}
