package io.wisoft.foodie.project.domain.post.application;

import io.wisoft.foodie.project.domain.account.persistance.AccountEntity;
import io.wisoft.foodie.project.domain.account.persistance.AccountRepository;
import io.wisoft.foodie.project.domain.post.persistance.PostEntity;
import io.wisoft.foodie.project.domain.post.persistance.PostRepository;
import io.wisoft.foodie.project.domain.post.web.dto.req.PostRegisterRequest;
import io.wisoft.foodie.project.domain.post.web.dto.res.PostResponseDto;
import io.wisoft.foodie.project.domain.post.web.dto.req.PostUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public Long registerPost(PostRegisterRequest requestDto) {

        AccountEntity author = accountRepository.findById(requestDto.getAuthorId())
                .orElseThrow();

        return postRepository.save(requestDto.toEntity(author)).getId();

    }

    @Transactional
    public Long updatePost(Long id, PostUpdateRequest requestDto) {

        PostEntity postEntity = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        postEntity.update(requestDto.getTitle(), requestDto.getContent());

        return id;

    }

    public PostResponseDto findByPostId(Long id){

        PostEntity entity = postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostResponseDto(entity);

    }

    public List<PostEntity> findPosts() {
        return postRepository.findAll();
    }

    public PostEntity findOnePost(Long postId) {
        return postRepository.getReferenceById(postId);
    }

}
