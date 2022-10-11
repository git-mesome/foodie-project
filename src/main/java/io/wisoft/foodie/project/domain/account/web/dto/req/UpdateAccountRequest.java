package io.wisoft.foodie.project.domain.account.web.dto.req;

public record UpdateAccountRequest(String nickname,
                                   String phoneNumber,
                                   String siDo,
                                   String siGunGu,
                                   String eupMyeonDong) {}
