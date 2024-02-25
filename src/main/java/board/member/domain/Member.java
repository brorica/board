package board.member.domain;

import board.common.BaseTimeEntity;
import board.post.domain.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public Member(final String email, final String password) {
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    protected Member() {
    }
}
