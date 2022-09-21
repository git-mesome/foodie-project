package io.wisoft.foodie.project.domain.post.persistance;

import io.wisoft.foodie.project.domain.BaseTimeEntity;
import io.wisoft.foodie.project.domain.account.persistance.AccountEntity;

import io.wisoft.foodie.project.domain.image.persistance.PostImage;
import io.wisoft.foodie.project.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "post")
public class PostEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private AccountEntity author;

    @Column(name = "title", length = 500, nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "hit")
    private int hit;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taker_id")
    private AccountEntity taker;

    @Enumerated(EnumType.STRING)
    @Column(name = "deal_status")
    private DealStatus dealStatus;

    @CreatedDate
    @Column(name = "last_deal_date")
    private LocalDateTime lastDealDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "post_type")
    private PostType postType;

    @OneToMany(mappedBy = "post")
    private List<PostImage> postImages = new ArrayList<>();

    @Builder
    public PostEntity( String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static PostEntity from(final Post post) {
        return new PostEntity(
                post.getTitle(),
                post.getContent()
        );
    }

    public Post toDomain() {
        return new Post(
                this.id,
                this.title,
                this.content
        );
    }

    public void update(String title, String content) {

        this.title = title;
        this.content = content;

    }


}
