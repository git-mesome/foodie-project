package io.wisoft.foodie.project.account.service;

import io.wisoft.foodie.project.account.Account;
import io.wisoft.foodie.project.account.domain.AccountRepository;
import io.wisoft.foodie.project.account.web.dto.res.FindAccountResponse;
import io.wisoft.foodie.project.handler.exception.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional(readOnly = true)
    public FindAccountResponse findAccountById(final Long accountId) {

        final Account account = accountRepository.findById(accountId)
                .orElseThrow(
                        () -> new AccountNotFoundException(accountId + "에 해당하는 사용자가 없습니다.")
                ).toDomain();

        return new FindAccountResponse(
                account.getName(),
                account.getEmail()
        );

    }


}
