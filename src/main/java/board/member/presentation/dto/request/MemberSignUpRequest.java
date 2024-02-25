package board.member.presentation.dto.request;

import board.member.domain.Member;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberSignUpRequest {

    private final String email;

    private final String password;

    public MemberSignUpRequest( @JsonProperty("email") final String email,
                                @JsonProperty("password") final String password) {
        this.email = email;
        this.password = password;
    }

    public Member toEntity() {
        return new Member(email, password);
    }

    public String getEmail() {
        return email;
    }
}
