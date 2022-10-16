package io.wisoft.foodie.project.domain.account.web.dto.res;

import io.wisoft.foodie.project.domain.post.persistance.Grade;

public record FindAccountInfoResponse(Long accountId,
                                      String nickname,
                                      String email,
                                      String profileImagePath,
                                      String phoneNumber,
                                      Grade grade,
                                      String siDo,
                                      String siGunGu,
                                      String eupMyeonDong) {
}
