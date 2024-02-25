package board.member.presentation.dto.request;

import board.member.domain.Member;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberSignInRequest {

    private final String email;

    private final String password;

    public MemberSignInRequest( @JsonProperty("email") final String email,
                                @JsonProperty("password") final String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
