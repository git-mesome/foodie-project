package io.wisoft.foodie.project.domain.account.application;

import io.wisoft.foodie.project.domain.account.persistance.AccountEntity;
import io.wisoft.foodie.project.domain.account.persistance.AccountRepository;
import io.wisoft.foodie.project.handler.exception.AccountNotFoundException;
import io.wisoft.foodie.project.domain.account.Account;
import io.wisoft.foodie.project.domain.account.web.dto.res.FindAccountListResponse;
import io.wisoft.foodie.project.domain.account.web.dto.res.FindAccountResponse;
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
    public FindAccountListResponse findAccountList() {

        return FindAccountListResponse.builder()
                .accountList(accountRepository.findAll().stream().map(AccountEntity::toDomain).toList())
                .build();


    }

    @Transactional(readOnly = true)
    public FindAccountResponse findAccountById(final Long accountId) {

        final Account account = accountRepository.findById(accountId)
                .orElseThrow(
                        () -> new AccountNotFoundException(accountId + "에 해당하는 사용자가 없습니다.")
                )
                .toDomain();

        return new FindAccountResponse(
                account.getEmail(),
                account.getNickName()
        );

    }


}
