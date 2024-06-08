package board.vote.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class VoteCreateParam {

    private final String name;

    private final LocalDateTime startAt;

    public VoteCreateParam(@JsonProperty("name") final String name,
                           @JsonProperty("startAt") final LocalDateTime startAt) {
        this.name = name;
        this.startAt = startAt;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }


}
