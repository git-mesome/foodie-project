package io.wisoft.foodie.project.domain.post.web;

import io.wisoft.foodie.project.domain.post.web.dto.req.UpdatePostRequest;
import io.wisoft.foodie.project.domain.post.web.dto.req.RegisterPostRequest;
import io.wisoft.foodie.project.domain.post.web.dto.res.FindByPostIdResponse;
import io.wisoft.foodie.project.domain.post.web.dto.res.RegisterPostResponse;
import io.wisoft.foodie.project.domain.post.application.PostService;
import io.wisoft.foodie.project.domain.image.S3Util;
import io.wisoft.foodie.project.global.resolver.AccountIdentifier;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostController {

    private final PostService postService;
    private final S3Util s3Util;

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody UpdatePostRequest request) {

        return ResponseEntity
                .ok(postService.updatePost(id, request));

    }

    @GetMapping("/{id}")
    public ResponseEntity<FindByPostIdResponse> findById(@PathVariable Long id) {

        return ResponseEntity
                .ok(postService.findByPostId(id));

    }

    @PostMapping
    public ResponseEntity<RegisterPostResponse> register(@RequestPart(value = "imagePath", required = false)final Optional<List<MultipartFile>> multipartFiles,
                                                         @RequestPart(value = "postContents") final RegisterPostRequest registerRequest,
                                                         @AccountIdentifier final Long authorId) throws IOException {

        List<String> imagePaths = s3Util.uploadFileList(multipartFiles.orElse(Collections.emptyList()), "post");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.registerPost(registerRequest, imagePaths, authorId));

    }

//    @GetMapping
//    public ResponseEntity<PostListResponse> findAllDesc(){
//
//        return ResponseEntity()
//
//    }

}
