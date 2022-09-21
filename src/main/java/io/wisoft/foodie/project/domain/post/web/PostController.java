package io.wisoft.foodie.project.domain.post.web;

import io.wisoft.foodie.project.domain.post.web.dto.req.PostUpdateRequest;
import io.wisoft.foodie.project.domain.post.web.dto.req.PostRegisterRequest;
import io.wisoft.foodie.project.domain.post.web.dto.res.FindByPostIdResponse;
import io.wisoft.foodie.project.domain.post.web.dto.res.RegisterPostResponse;
import io.wisoft.foodie.project.domain.post.application.PostService;
import io.wisoft.foodie.project.domain.image.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostController {

    private final PostService postService;
    private final S3Util s3Util;

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody PostUpdateRequest request) {

        return ResponseEntity
                .ok(postService.updatePost(id, request));

    }

    @GetMapping("/{id}")
    public ResponseEntity<FindByPostIdResponse> findById(@PathVariable Long id) {

        return ResponseEntity
                .ok(postService.findByPostId(id));

    }

//    @PostMapping
//    public ResponseEntity<RegisterPostResponse> register(@RequestParam("imagePath") List<MultipartFile> multipartFiles,
//                                                         @RequestParam("postContents") PostRegisterRequest request) throws IOException {
//
//        List<String> imgPaths = s3Util.uploadFileList(multipartFiles, "post");
//        System.out.println("IMG 경로들 : " + imgPaths);
//
//        return ResponseEntity
//                .ok(postService.registerPost(request,imgPaths));
//
//
//    }


}
