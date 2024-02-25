package board.member.presentation.dto;

public class LoginMemberInfo {

    private final Long id;

    private final String email;

    public LoginMemberInfo(final Long id, final String email) {
        this.id = id;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}

