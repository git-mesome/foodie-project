package io.wisoft.foodie.project.domain.post.web;

import io.wisoft.foodie.project.domain.account.web.dto.res.DeleteAccountResponse;
import io.wisoft.foodie.project.domain.post.persistance.PostType;
import io.wisoft.foodie.project.domain.post.web.dto.req.DeletePostImageRequest;
import io.wisoft.foodie.project.domain.post.web.dto.req.UpdatePostRequest;
import io.wisoft.foodie.project.domain.post.web.dto.req.RegisterPostRequest;
import io.wisoft.foodie.project.domain.post.web.dto.res.*;
import io.wisoft.foodie.project.domain.post.application.PostService;
import io.wisoft.foodie.project.domain.image.S3Util;
import io.wisoft.foodie.project.global.resolver.AccountIdentifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequestMapping("/posts")
@RestController
public class PostController {

    private final PostService postService;
    private final S3Util s3Util;

    public PostController(final PostService postService,
                          final S3Util s3Util) {
        this.postService = postService;
        this.s3Util = s3Util;
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

    @PostMapping
    public ResponseEntity<RegisterPostResponse> register(@RequestPart(value = "imagePath", required = false) final Optional<List<MultipartFile>> multipartFiles,
                                                         @RequestPart(value = "postContents") final RegisterPostRequest request,
                                                         @AccountIdentifier final Long authorId) throws IOException {

        List<String> imagePaths = s3Util.uploadFileList(multipartFiles.orElse(Collections.emptyList()), "post");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postService.register(request, imagePaths, authorId));

    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdatePostResponse> update(@PathVariable final Long id,
                                                     @RequestBody final UpdatePostRequest request,
                                                     @AccountIdentifier final Long authorId) {
        return ResponseEntity
                .ok(postService.update(id, request, authorId));
    }

    @PutMapping("/{id}/images")
    public ResponseEntity<UpdatePostResponse> updateImages(@PathVariable final Long id,
                                                           @RequestPart(value = "deleteImages") final DeletePostImageRequest request,
                                                           @RequestPart(value = "imagePath", required = false) final Optional<List<MultipartFile>> multipartFiles,
                                                           @AccountIdentifier final Long authorId) throws IOException {

        s3Util.deleteFileList(request.imageNameList());
        List<String> imagePaths = s3Util.uploadFileList(multipartFiles.orElse(Collections.emptyList()), "post");

        return ResponseEntity
                .ok(postService.updateImages(id, request, imagePaths, authorId));
    }


    @PostMapping("/{id}/likes")
    public ResponseEntity<LikesResponse> likes(@PathVariable final Long id,
                                               @AccountIdentifier final Long accountId) {
        return ResponseEntity
                .ok(postService.likes(id, accountId));
    }

    @DeleteMapping("/{id}/likes")
    public ResponseEntity<LikesResponse> unlikes(@PathVariable final Long id,
                                                 @AccountIdentifier final Long accountId) {
        return ResponseEntity
                .ok(postService.unlikes(id, accountId));
    }

}
