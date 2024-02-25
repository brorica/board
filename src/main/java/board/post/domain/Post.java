package board.post.domain;

import board.common.BaseTimeEntity;
import board.member.domain.Member;
import board.post.presentation.request.PostCreateRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String title;

    private String content;

    private Boolean delete;

    public Post(final Member member, final PostCreateRequest postCreateRequest) {
        this.member = member;
        this.title = postCreateRequest.getTitle();
        this.content = postCreateRequest.getContent();
        this.delete = false;
    }

    public void validateAndDelete(final Long memberId) {
        if (this.member.getId() != memberId) {
            throw new RuntimeException("작성자가 아닙니다.");
        }
        if(this.delete) {
            throw new RuntimeException("존재하지 않는 글입니다.");
        }
        this.delete = true;
    }

    public Long getId() {
        return id;
    }

    protected Post() {
    }
}
