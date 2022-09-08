package io.wisoft.foodie.project.domain.post;

import io.wisoft.foodie.project.domain.account.Account;
import io.wisoft.foodie.project.domain.Category;
import io.wisoft.foodie.project.domain.DealStatus;
import io.wisoft.foodie.project.domain.PostType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Author_id")
    private Account author;

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

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taker_id")
    private Account taker;

    @Enumerated(EnumType.STRING)
    @Column(name = "deal_status")
    private DealStatus dealStatus;

    @Column(name = "last_deal_date")
    private Date lastDealDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "post_type")
    private PostType postType;


    @Builder
    public Post(Account author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {

        this.title = title;
        this.content = content;

    }


    public void setAuthor(Account author) {
        this.author = author;
    }


    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public void setDealStatus(DealStatus dealStatus) {
        this.dealStatus = dealStatus;
    }


}
