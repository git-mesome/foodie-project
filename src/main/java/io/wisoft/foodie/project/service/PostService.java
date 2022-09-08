package io.wisoft.foodie.project.service;

import io.wisoft.foodie.project.domain.account.Account;
import io.wisoft.foodie.project.domain.post.Post;
import io.wisoft.foodie.project.domain.account.AccountRepository;
import io.wisoft.foodie.project.domain.post.PostRepository;
import io.wisoft.foodie.project.web.dto.req.PostRegisterRequestDto;
import io.wisoft.foodie.project.web.dto.res.PostResponseDto;
import io.wisoft.foodie.project.web.dto.req.PostUpdateRequestDto;
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
    public Long registerPost(PostRegisterRequestDto requestDto) {

        Account author = accountRepository.findById(requestDto.getAuthorId())
                .orElseThrow();

        return postRepository.save(requestDto.toEntity(author)).getId();

    }

    @Transactional
    public Long updatePost(Long id, PostUpdateRequestDto requestDto) {

        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        post.update(requestDto.getTitle(), requestDto.getContent());

        return id;

    }

    public PostResponseDto findByPostId(Long id){

        Post entity = postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostResponseDto(entity);

    }

    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    public Post findOnePost(Long postId) {
        return postRepository.getReferenceById(postId);
    }

}
