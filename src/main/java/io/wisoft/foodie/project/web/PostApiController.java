package io.wisoft.foodie.project.web;

import io.wisoft.foodie.project.service.PostService;
import io.wisoft.foodie.project.web.dto.PostRegisterRequestDto;
import io.wisoft.foodie.project.web.dto.PostResponseDto;
import io.wisoft.foodie.project.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    @PostMapping("/posts")
    public Long register(@RequestBody PostRegisterRequestDto requestDto) {

        return postService.registerPost(requestDto);

    }

    @PutMapping("/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) {

        return postService.updatePost(id, requestDto);

    }

    @GetMapping("/posts/{id}")
    public PostResponseDto findById(@PathVariable Long id) {

        return postService.findByPostId(id);

    }

}
