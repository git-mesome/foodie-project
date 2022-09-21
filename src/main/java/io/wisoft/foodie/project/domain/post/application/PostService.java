package io.wisoft.foodie.project.domain.post.application;

import io.wisoft.foodie.project.domain.account.persistance.AccountEntity;
import io.wisoft.foodie.project.domain.account.persistance.AccountRepository;
import io.wisoft.foodie.project.domain.image.persistance.ImageRepository;
import io.wisoft.foodie.project.domain.image.persistance.PostImage;
import io.wisoft.foodie.project.domain.post.Post;
import io.wisoft.foodie.project.domain.post.persistance.PostEntity;
import io.wisoft.foodie.project.domain.post.persistance.PostRepository;
import io.wisoft.foodie.project.domain.post.web.dto.req.PostRegisterRequest;
import io.wisoft.foodie.project.domain.post.web.dto.res.FindByPostIdResponse;
import io.wisoft.foodie.project.domain.post.web.dto.res.RegisterPostResponse;
import io.wisoft.foodie.project.domain.post.web.dto.req.PostUpdateRequest;
import io.wisoft.foodie.project.domain.image.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final AccountRepository accountRepository;
    private final ImageRepository imageRepository;
    private final S3Util s3Util;

//    @Transactional
//    public RegisterPostResponse registerPost(final PostRegisterRequest request, List<String> imagePath) {
//
//        //Todo 로그인한 유저만 게시글 작성
////        AccountEntity account = accountRepository.findByNickName();
//
//        PostEntity post = postRepository.save(PostEntity.builder()
//                .title(request.getTitle())
//                .content(request.getContent())
//                .build());
//
//        List<String> imageList = new ArrayList<>();
//
//        for (String imageUrl : imagePath) {
//            PostImage image = new PostImage(post, imageUrl);
//            imageRepository.save(image);
//            imageList.add(image.getPostImagePath());
//        }
//
//
//    }

    @Transactional
    public Long updatePost(Long id, PostUpdateRequest requestDto) {

        PostEntity postEntity = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        postEntity.update(requestDto.getTitle(), requestDto.getContent());

        return id;

    }

    public FindByPostIdResponse findByPostId(Long id) {

        PostEntity entity = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new FindByPostIdResponse(entity);

    }

    public List<PostEntity> findPosts() {
        return postRepository.findAll();
    }

    public PostEntity findOnePost(Long postId) {
        return postRepository.getReferenceById(postId);
    }

}
