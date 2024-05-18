package board.post.domain;

import board.common.BaseTimeEntity;
import board.member.domain.Member;
import board.post.exception.PostNotFoundException;
import board.post.exception.PostNotOwner;
import board.post.presentation.request.PostCreateRequest;
import board.post.presentation.request.PostUpdateRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import static board.common.exception.ForbiddenException.POST_NOT_OWNER;
import static board.common.exception.NotFoundException.POST_NOT_FOUND;

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

    @Column(nullable = false)
    private Boolean delete;

    @Column(nullable = false)
    private Long viewCount;

    public Post(final Member member, final PostCreateRequest postCreateRequest) {
        this.member = member;
        this.title = postCreateRequest.getTitle();
        this.content = postCreateRequest.getContent();
        this.delete = false;
        this.viewCount = 0L;
    }

    public void delete(final Long memberId) {
        validate(memberId);
        this.delete = true;
    }

    public void update(final Long memberId, final PostUpdateRequest postUpdateRequest) {
        validate(memberId);
        this.title = postUpdateRequest.getTitle();
        this.content = postUpdateRequest.getContent();
    }

    private void validate(final Long memberId) {
        if (this.member.getId() != memberId) {
            throw new PostNotOwner(POST_NOT_OWNER);
        }
        if(this.delete) {
            throw new PostNotFoundException(POST_NOT_FOUND);
        }
    }

    public Boolean isAuth(final Long memberId) {
        if (this.member.getId() == memberId) {
            return true;
        }
        return false;
    }

    public void increaseViewCount() {
        this.viewCount += 1;
    }

    public Long getId() {
        return id;
    }

    public String getCreator() {
        return member.getEmail();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Boolean getDelete() {
        return delete;
    }

    public Long getViewCount() {
        return viewCount;
    }

    protected Post() {
    }
}
