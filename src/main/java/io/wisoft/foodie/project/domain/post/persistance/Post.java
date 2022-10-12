package io.wisoft.foodie.project.domain.post.persistance;

import io.wisoft.foodie.project.domain.BaseTimeEntity;
import io.wisoft.foodie.project.domain.account.persistance.Account;

import io.wisoft.foodie.project.domain.image.persistance.PostImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@NoArgsConstructor
@DynamicInsert
@Entity
@Table(name = "post")
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Account author;

    @Column(name = "title", length = 500, nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private Integer hit;

    @Column(name = "expiration_date")
    private String expirationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taker_id")
    private Account taker;

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

    public Post(final Account author,
                final String title,
                final String content,
                final Category category,
                final String expirationDate,
                final PostType postType) {

        this.author = author;
        this.title = title;
        this.content = content;
        this.category = category;
        this.expirationDate = expirationDate;
        this.postType = postType;

    }

    public void update(final String title, final String content) {

        this.title = title;
        this.content = content;

    }

//    public void mappingCategory(Category category) {
//        this.category = category;
//        category.mappingPost(this);
//    }


}
