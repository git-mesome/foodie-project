package io.wisoft.foodie.project.domain.post.application;

import io.wisoft.foodie.project.domain.account.persistance.Account;
import io.wisoft.foodie.project.domain.account.persistance.AccountRepository;
import io.wisoft.foodie.project.domain.image.persistance.ImageRepository;
import io.wisoft.foodie.project.domain.image.persistance.PostImage;
import io.wisoft.foodie.project.domain.post.persistance.*;
import io.wisoft.foodie.project.domain.post.web.dto.req.RegisterPostRequest;
import io.wisoft.foodie.project.domain.post.web.dto.req.UpdatePostRequest;
import io.wisoft.foodie.project.domain.post.web.dto.res.FindAllPostsResponse;
import io.wisoft.foodie.project.domain.post.web.dto.res.FindPostDetailResponse;
import io.wisoft.foodie.project.domain.post.web.dto.res.RegisterPostResponse;
import io.wisoft.foodie.project.exception.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class PostService {

    private final PostRepository postRepository;
    private final AccountRepository accountRepository;
    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public PostService(final PostRepository postRepository,
                       final AccountRepository accountRepository,
                       final ImageRepository imageRepository,
                       final CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.accountRepository = accountRepository;
        this.imageRepository = imageRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public RegisterPostResponse register(final RegisterPostRequest request,
                                         final List<String> imagePath,
                                         final Long id) {

        final Account account = accountRepository.findById(id).orElseThrow(
                () -> new AccountNotFoundException("존재하지 않는 회원정보입니다.")
        );
        final Category category = categoryRepository.findByName(request.category());

        final Post post = postRepository.save(new Post(
                account,
                request.title(),
                request.content(),
                category,
                request.expirationDate(),
                request.getPostType()
        ));

        final List<PostImage> imageList = new ArrayList<>();

        for (String imageUrl : imagePath) {
            PostImage image = new PostImage(post, imageUrl);
            imageRepository.save(image);
            imageList.add(image);
        }

        return new RegisterPostResponse(
                post.getId());
    }

    @Transactional
    public Long update(final Long id, final UpdatePostRequest requestDto) {

        final Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        post.update(requestDto.title(), requestDto.content());

        return id;

    }

    public FindPostDetailResponse findById(final Long id) {
        final Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new FindPostDetailResponse(
                post.getId(),
                post.getAuthor().getNickname(),
                post.getAuthor().getProfileImagePath(),
                post.getAuthor().getGrade(),
                post.getTitle(),
                post.getContent(),
                post.getHit(),
                post.getAuthor().getSiDo(),
                post.getAuthor().getSiGunGu(),
                post.getAuthor().getEupMyeonDong(),
                post.getDealStatus(),
                post.getCreateDate(),
                post.getPostImages()
                        .stream()
                        .map(PostImage::getPostImagePath)
                        .toList()
        );

    }

    @Transactional(readOnly = true)
    public List<FindAllPostsResponse> findAll(final PostType postType) {
        List<Post> postList = this.postRepository.findByPostTypeOrderByCreateDateDesc(postType);
        return postList.stream()
                .map(post -> new FindAllPostsResponse(
                        post.getId(),
                        post.getAuthor().getNickname(),
                        post.getTitle(),
                        post.getUpdateDate()))
                .toList();
    }

    public Post findOnePost(final Long postId) {
        return postRepository.getReferenceById(postId);
    }

}
