package io.wisoft.foodie.project.web.dto.res;

import lombok.Getter;

@Getter
public class FindAccountResponse {

    private String name;
    private String email;

    public FindAccountResponse(String name, String email) {
        this.name = name;
        this.email = email;
    }

}
