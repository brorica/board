package board.vote;

import board.common.BaseTimeEntity;
import board.vote.presentation.dto.VoteCreateParam;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Vote extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Long voteCount;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    public Vote(final String name, final LocalDateTime startAt) {
        this.name = name;
        this.voteCount = 0L;
        this.startAt = startAt;
        this.endAt = startAt.plusDays(365);
    }

    public Vote(final VoteCreateParam voteCreateParam) {
        this.name = voteCreateParam.getName();
        this.voteCount = 0L;
        this.startAt = voteCreateParam.getStartAt();
        this.endAt = startAt.plusDays(365);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    protected Vote() {
    }
}
