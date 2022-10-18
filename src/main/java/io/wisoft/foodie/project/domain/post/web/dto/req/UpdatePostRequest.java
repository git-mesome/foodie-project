package io.wisoft.foodie.project.domain.post.web.dto.req;

public record UpdatePostRequest(String title,
                                String content,
                                String category) {}
