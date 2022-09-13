package io.wisoft.foodie.project.domain.account.application;

import io.wisoft.foodie.project.domain.account.persistance.AccountEntity;
import io.wisoft.foodie.project.domain.account.persistance.AccountRepository;
import io.wisoft.foodie.project.handler.exception.AccountNotFoundException;
import io.wisoft.foodie.project.domain.account.Account;
import io.wisoft.foodie.project.domain.account.web.dto.req.SignInRequest;
import io.wisoft.foodie.project.domain.account.web.dto.req.SignUpRequest;
import io.wisoft.foodie.project.domain.account.web.dto.res.SignInResponse;
import io.wisoft.foodie.project.domain.account.web.dto.res.SignUpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final AccountRepository accountRepository;

    @Autowired
    public AuthService(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public SignUpResponse signUp(final SignUpRequest request){

        Account account = new Account(
                request.getEmail(),
                request.getPassword(),
                request.getNickname()
        );

        account = accountRepository.save(AccountEntity.from(account))
                .toDomain();

        return new SignUpResponse(account.getId());

    }

    @Transactional(readOnly = true)
    public SignInResponse signIn(final SignInRequest request){

        final Account account = accountRepository.findByEmail(request.getEmail())
                .orElseThrow(
                        () -> new AccountNotFoundException(request.getEmail() + "에 해당하는 사용자를 찾을 수 없습니다.")
                )
                .toDomain();

        account.checkPassword(request.getPassword());

        return new SignInResponse(account.getId());

    }

}
