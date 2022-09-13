package io.wisoft.foodie.project.domain.post.persistance;

import io.wisoft.foodie.project.domain.account.persistance.AccountEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Author_id")
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

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taker_id")
    private AccountEntity taker;

    @Enumerated(EnumType.STRING)
    @Column(name = "deal_status")
    private DealStatus dealStatus;

    @Column(name = "last_deal_date")
    private Date lastDealDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "post_type")
    private PostType postType;


    @Builder
    public PostEntity(AccountEntity author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {

        this.title = title;
        this.content = content;

    }


    public void setAuthor(AccountEntity author) {
        this.author = author;
    }


    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public void setDealStatus(DealStatus dealStatus) {
        this.dealStatus = dealStatus;
    }


}
