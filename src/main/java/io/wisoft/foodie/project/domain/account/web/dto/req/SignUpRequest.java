package io.wisoft.foodie.project.domain.account.web.dto.req;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SignUpRequest {

    @NotBlank(message = "이메일은 필수 항목입니다.")
    @Email
    private String email;

    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    private String password;

    @NotBlank(message = "별명은 필수 항목입니다.")
    private String nickname;

}
