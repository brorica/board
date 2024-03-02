package board.member.presentation.dto;

import board.member.domain.Member;

public class LoginMemberInfo {

    private final Long id;

    private final String email;

    public LoginMemberInfo(final Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}

