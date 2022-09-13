package io.wisoft.foodie.project.domain.account.web.dto.res;

import io.wisoft.foodie.project.domain.account.Account;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FindAccountListResponse {
    private final List<Account> accountList;

    public FindAccountListResponse(final List<Account> accountList) {

        this.accountList = accountList;

    }

}
