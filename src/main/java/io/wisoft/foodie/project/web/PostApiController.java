package io.wisoft.foodie.project.web;

import io.wisoft.foodie.project.service.PostService;
import io.wisoft.foodie.project.web.dto.req.PostRegisterRequestDto;
import io.wisoft.foodie.project.web.dto.res.PostResponseDto;
import io.wisoft.foodie.project.web.dto.req.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostApiController {

    private final PostService postService;

    @PostMapping
    public Long register(@RequestBody PostRegisterRequestDto requestDto) {

        return postService.registerPost(requestDto);

    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) {

        return postService.updatePost(id, requestDto);

    }

    @GetMapping("/{id}")
    public PostResponseDto findById(@PathVariable Long id) {

        return postService.findByPostId(id);

    }

}
