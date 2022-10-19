package io.wisoft.foodie.project.domain.post.web.dto.req;

import java.util.List;

public record DeletePostImageRequest(List<String> imageNameList) {
}
