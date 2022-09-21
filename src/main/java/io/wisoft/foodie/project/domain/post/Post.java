package io.wisoft.foodie.project.domain.post;

import io.wisoft.foodie.project.domain.account.Account;
import io.wisoft.foodie.project.exception.Code;
import io.wisoft.foodie.project.exception.PrivateException;
import lombok.Getter;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class Post {
    private Long id;
    private String title;
    private String content;

    private List<MultipartFile> postImage;

    public Post(
            final String title,
            final String content
    ) {
        this(null, title, content);
    }

    public Post(final Long id,
                final String title,
                final String content
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    private void postBlankCheck(List<String> imagePath){

        if(imagePath == null || imagePath.isEmpty()){
        }
    }

//    // 게시글 작성
//    public Post(String content, Account account) {
//        if (!StringUtils.hasText(content)) {
//            throw new PrivateException(Code.WRONG_INPUT_CONTENT);
//        }
//        this.content = content;
//        this.account = account;
//
//    }

}
