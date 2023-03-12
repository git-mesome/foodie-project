package io.wisoft.foodie.project.domain.auth.web.dto.req;

import io.wisoft.foodie.project.domain.post.persistance.Grade;

public record SignUpRequest(String oauthId,
                            String email,
                            String profileImagePath,
                            String nickname,
                            String phoneNumber) {
//    public Grade getGrade() {
//        return Grade.valueOf(this.grade.toUpperCase());
//    }
}
