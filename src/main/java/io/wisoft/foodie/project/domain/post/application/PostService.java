package io.wisoft.foodie.project.domain.post.application;

import io.wisoft.foodie.project.domain.account.persistance.Account;
import io.wisoft.foodie.project.domain.account.persistance.AccountRepository;
import io.wisoft.foodie.project.domain.image.persistance.ImageRepository;
import io.wisoft.foodie.project.domain.image.persistance.PostImage;
import io.wisoft.foodie.project.domain.post.persistance.*;
import io.wisoft.foodie.project.domain.post.persistance.category.Category;
import io.wisoft.foodie.project.domain.post.persistance.category.CategoryRepository;
import io.wisoft.foodie.project.domain.post.persistance.likes.Likes;
import io.wisoft.foodie.project.domain.post.persistance.likes.LikesRepository;
import io.wisoft.foodie.project.domain.post.web.dto.req.DeletePostImageRequest;
import io.wisoft.foodie.project.domain.post.web.dto.req.RegisterPostRequest;
import io.wisoft.foodie.project.domain.post.web.dto.req.UpdatePostRequest;
import io.wisoft.foodie.project.domain.post.web.dto.res.*;
import io.wisoft.foodie.project.exception.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PostService {

    private final PostRepository postRepository;
    private final AccountRepository accountRepository;
    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;
    private final LikesRepository likesRepository;

    @Autowired
    public PostService(final PostRepository postRepository,
                       final AccountRepository accountRepository,
                       final ImageRepository imageRepository,
                       final CategoryRepository categoryRepository,
                       final LikesRepository likesRepository) {
        this.postRepository = postRepository;
        this.accountRepository = accountRepository;
        this.imageRepository = imageRepository;
        this.categoryRepository = categoryRepository;
        this.likesRepository = likesRepository;
    }

    @Transactional
    public RegisterPostResponse register(final RegisterPostRequest request,
                                         final List<String> imagePath,
                                         final Long authorId) {

        final Account account = accountRepository.findById(authorId)
                .orElseThrow(() -> new AccountNotFoundException("존재하지 않는 회원정보입니다."));
        final Category category = categoryRepository.findByName(request.category());

        final Post post = postRepository.save(new Post(
                account,
                request.title(),
                request.content(),
                category,
                request.getPostType()
        ));

        final List<PostImage> imageList = new ArrayList<>();

        for (String imageUrl : imagePath) {
            PostImage image = new PostImage(post, imageUrl);
            imageRepository.save(image);
            imageList.add(image);
        }

        return new RegisterPostResponse(post.getId());
    }

    @Transactional
    public UpdatePostResponse updateImages(final Long id,
                                           final DeletePostImageRequest request,
                                           final List<String> imagePathList,
                                           final Long authorId) {
        final Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));

        accountRepository.findById(authorId)
                .orElseThrow(() -> new AccountNotFoundException("존재하지 않는 회원정보입니다."));

        final List<PostImage> imageList = new ArrayList<>();

        for (String image : request.imageNameList()) {

            imageRepository.deleteByPostIdAndPostImagePathEndsWith(post.getId(), image);
        }

        for (String imagePath : imagePathList) {
            PostImage image = new PostImage(post, imagePath);
            imageList.add(image);
        }
        imageRepository.saveAll(imageList);

        return new UpdatePostResponse(post.getId());

    }


    @Transactional
    public FindPostDetailResponse findById(final Long id, final Long accountId) {

        final Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        post.increaseHit();

        return new FindPostDetailResponse(
                post.getId(),
                post.getAuthor().getNickname(),
                post.getAuthor().getProfileImagePath(),
                post.getAuthor().getGrade(),
                post.getTitle(),
                post.getContent(),
                post.getHit(),
                checkLikeStateByAccountIdAndPostId(accountId, post.getId()),
                post.getLikesCount(),
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
    public List<FindAllPostsResponse> findAll(final PostType postType, final Long accountId) {

        List<Post> postList = this.postRepository.findByPostTypeOrderByCreateDateDesc(postType);

        return postList.stream()
                .map(post -> new FindAllPostsResponse(
                        post.getId(),
                        post.getAuthor().getNickname(),
                        post.getTitle(),
                        checkLikeStateByAccountIdAndPostId(accountId, post.getId()),
                        post.getLikesCount(),
                        post.getUpdateDate()))
                .toList();

    }

    @Transactional
    public UpdatePostResponse update(final Long id, final UpdatePostRequest request, final Long authorId) {

        accountRepository.findById(authorId)
                .orElseThrow(() -> new AccountNotFoundException("존재하지 않는 회원정보입니다."));
        final Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));
        final Category category = categoryRepository.findByName(request.category());

        post.update(
                request.title(),
                request.content(),
                category
        );

        postRepository.save(post);

        return new UpdatePostResponse(post.getId());

    }

    private Boolean checkLikeStateByAccountIdAndPostId(final Long accountId, final Long postId) {
        final Boolean likesState;

        if (accountId == null) {
            likesState = false;
        } else {
            Optional<Likes> likes = likesRepository.findLikesByAccountIdAndPostId(accountId, postId);
            likesState = likes.isPresent();
        }
        return likesState;
    }

    @Transactional
    public LikesResponse likes(final Long id, final Long accountId) {

        final Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("존재하지 않는 회원정보입니다."));
        final Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        if (likesRepository.findLikesByAccountIdAndPostId(account.getId(), post.getId()).isPresent()) {
            throw new IllegalStateException("이미 찜한 게시글입니다.");
        }

        final Likes likes = likesRepository.save(new Likes(account, post));

        postRepository.updateLikesCount(post.getId(), 1);

        return new LikesResponse(
                likes.getId(),
                post.getLikesCount() + 1
        );
    }

    @Transactional
    public LikesResponse unlikes(final Long id, final Long accountId) {

        final Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("존재하지 않는 회원정보입니다."));
        final Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        final Likes likes = likesRepository.findLikesByAccountIdAndPostId(account.getId(), post.getId())
                .orElseThrow();


        likesRepository.delete(likes);
        postRepository.updateLikesCount(post.getId(), -1);

        return new LikesResponse(likes.getId(), post.getLikesCount() - 1);
    }

    @Transactional
    public DeletePostResponse delete(final Long id,final Long accountId) {

        final Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("존재하지 않는 회원정보입니다."));
        final Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postRepository.deleteById(post.getId());

        return new DeletePostResponse(post.getId());

    }

}
