package io.wisoft.foodie.project.domain.post.web;

import io.wisoft.foodie.project.domain.post.web.dto.req.PostRegisterRequest;
import io.wisoft.foodie.project.domain.post.web.dto.req.PostUpdateRequest;
import io.wisoft.foodie.project.domain.post.web.dto.res.PostResponseDto;
import io.wisoft.foodie.project.domain.post.application.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostApiController {

    private final PostService postService;

    @PostMapping
    public Long register(@RequestBody PostRegisterRequest requestDto) {

        return postService.registerPost(requestDto);

    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequest requestDto) {

        return postService.updatePost(id, requestDto);

    }

    @GetMapping("/{id}")
    public PostResponseDto findById(@PathVariable Long id) {

        return postService.findByPostId(id);

    }

}
