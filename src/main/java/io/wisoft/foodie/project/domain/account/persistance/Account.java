package io.wisoft.foodie.project.domain.account.persistance;

import io.wisoft.foodie.project.domain.BaseTimeEntity;
import io.wisoft.foodie.project.domain.chat.persistance.ChatRoom;
import io.wisoft.foodie.project.domain.post.persistance.Grade;
import io.wisoft.foodie.project.domain.post.persistance.Post;
import io.wisoft.foodie.project.domain.post.persistance.likes.Likes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@DynamicInsert
@Entity
@Table(name = "account")
public class Account extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "oauth_id")
    private String oauthId;

    @Column(name = "email")
    private String email;

    @Column(name = "profile_image_path")
    private String profileImagePath;

    @Column(name = "nickname")
    private String nickname;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(name = "si_do")
    private String siDo;

    @Column(name = "si_gun_gu")
    private String siGunGu;

    @Column(name = "eup_myeon_dong")
    private String eupMyeonDong;

    @OneToMany(mappedBy = "author")
    private final List<Post> post = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    private final List<Likes> likes = new ArrayList<>();


    public Account(final String oauthId,
                   final String email,
                   final String profileImagePath,
                   final String nickname,
                   final String phoneNumber) {
        this(null, oauthId, email, profileImagePath, nickname, phoneNumber, null);
    }

    public Account(final Long id,
                   final String oauthId,
                   final String email,
                   final String profileImagePath,
                   final String nickname,
                   final String phoneNumber,
                   final Grade grade
    ) {
        this.id = id;
        this.oauthId = oauthId;
        this.email = email;
        this.profileImagePath = profileImagePath;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.grade = grade;

    }

    public void update(final String nickName,
                       final String phoneNumber,
                       final String siDo,
                       final String siGunGu,
                       final String eupMyeonDong) {
        this.nickname = nickName;
        this.phoneNumber = phoneNumber;
        this.siDo = siDo;
        this.siGunGu = siGunGu;
        this.eupMyeonDong = eupMyeonDong;

    }


}
