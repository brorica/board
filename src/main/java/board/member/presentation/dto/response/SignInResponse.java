package board.member.presentation.dto.response;

public class SignInResponse {

    private final String nickname;

    public SignInResponse(final String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
