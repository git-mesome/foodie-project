package io.wisoft.foodie.project.domain.post.web;

import io.wisoft.foodie.project.domain.image.application.S3Service;
import io.wisoft.foodie.project.domain.post.persistance.DealStatus;
import io.wisoft.foodie.project.domain.post.persistance.PostType;
import io.wisoft.foodie.project.domain.post.web.dto.req.DeletePostImageRequest;
import io.wisoft.foodie.project.domain.post.web.dto.req.UpdatePostRequest;
import io.wisoft.foodie.project.domain.post.web.dto.req.RegisterPostRequest;
import io.wisoft.foodie.project.domain.post.web.dto.res.*;
import io.wisoft.foodie.project.domain.post.application.PostService;
import io.wisoft.foodie.project.domain.post.web.dto.res.find.*;
import io.wisoft.foodie.project.global.resolver.AccountIdentifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequestMapping("/posts")
@RestController
public class PostController {

    private final PostService postService;
    private final S3Service s3Service;

    public PostController(final PostService postService,
                          final S3Service s3Service) {
        this.postService = postService;
        this.s3Service = s3Service;
    }

    @GetMapping
    public ResponseEntity<List<FindAllPostsResponse>> findAll(@RequestParam(value = "postType") final String postType,
                                                              @AccountIdentifier final Long accountId) {
        return ResponseEntity
                .ok(postService.findAll(PostType.valueOf(postType.toUpperCase()), accountId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindPostDetailResponse> findById(@PathVariable final Long id,
                                                           @AccountIdentifier final Long accountId) {
        return ResponseEntity
                .ok(postService.findById(id, accountId));
    }

    @GetMapping("/shared")
    public ResponseEntity<List<FindAllSharedPostsResponse>> findAllShared(@AccountIdentifier final Long authorId) {
        return ResponseEntity
                .ok(postService.findAllShared(authorId));
    }

    @GetMapping("/received")
    public ResponseEntity<List<FindAllReceivedPostsResponse>> findAllReceived(@AccountIdentifier final Long accountId) {
        return ResponseEntity
                .ok(postService.findAllReceived(accountId));
    }

    @GetMapping("/likes")
    public ResponseEntity<List<FindAllLikesResponse>> findAllLikes(@AccountIdentifier final Long accountId) {
        return ResponseEntity.ok(postService.findAllLikes(accountId));
    }

    @PostMapping
    public ResponseEntity<RegisterPostResponse> register(@RequestPart(value = "imagePath", required = false) final Optional<List<MultipartFile>> multipartFiles,
                                                         @RequestPart(value = "postContents") final RegisterPostRequest request,
                                                         @AccountIdentifier final Long authorId) throws IOException {

        final List<String> imagePaths = s3Service.uploadFileList(multipartFiles);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postService.register(request, imagePaths, authorId));

    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdatePostResponse> update(@PathVariable("id") final Long id,
                                                     @RequestBody final UpdatePostRequest request,
                                                     @AccountIdentifier final Long authorId) {
        return ResponseEntity
                .ok(postService.update(id, request, authorId));
    }

    @PutMapping("/{id}/images")
    public ResponseEntity<UpdatePostResponse> updateImages(@PathVariable("id") final Long id,
                                                           @RequestPart(value = "deleteImages") final DeletePostImageRequest request,
                                                           @RequestPart(value = "imagePath", required = false) final Optional<List<MultipartFile>> multipartFiles,
                                                           @AccountIdentifier final Long authorId) throws IOException {

        s3Service.deleteFileList(request.imageNameList());
        final List<String> imagePaths = s3Service.uploadFileList(multipartFiles);

        return ResponseEntity
                .ok(postService.updateImages(id, request, imagePaths, authorId));
    }

    @PutMapping("{id}/yet")
    public ResponseEntity<UpdatePostResponse> updateDealStatusYet(@PathVariable("id") final Long id,
                                                                  @AccountIdentifier final Long authorId) {
        return ResponseEntity
                .ok(postService.updateDealStatus(id, DealStatus.YET, authorId));
    }

    @PutMapping("{id}/book")
    public ResponseEntity<UpdatePostResponse> updateDealStatusBook(@PathVariable("id") final Long id,
                                                                   @AccountIdentifier final Long authorId) {
        return ResponseEntity
                .ok(postService.updateDealStatus(id, DealStatus.BOOK, authorId));
    }

    @PutMapping("{id}/finish")
    public ResponseEntity<UpdatePostResponse> updateDealStatusFinish(@PathVariable("id") final Long id,
                                                                     @AccountIdentifier final Long authorId) {
        return ResponseEntity
                .ok(postService.updateDealStatus(id, DealStatus.FINISH, authorId));
    }

    @PostMapping("/{id}/likes")
    public ResponseEntity<LikesResponse> likes(@PathVariable("id") final Long id,
                                               @AccountIdentifier final Long accountId) {
        return ResponseEntity
                .ok(postService.likes(id, accountId));
    }

    @DeleteMapping("/{id}/likes")
    public ResponseEntity<LikesResponse> unlikes(@PathVariable("id") final Long id,
                                                 @AccountIdentifier final Long accountId) {
        return ResponseEntity
                .ok(postService.unlikes(id, accountId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeletePostResponse> delete(@PathVariable("id") final Long id,
                                                     @RequestBody final DeletePostImageRequest request,
                                                     @AccountIdentifier final Long accountId) {

        s3Service.deleteFileList(request.imageNameList());

        return ResponseEntity
                .ok(postService.delete(id, accountId)
                );
    }

}
