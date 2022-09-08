package io.wisoft.foodie.project.service;

import io.wisoft.foodie.project.domain.account.Account;
import io.wisoft.foodie.project.domain.account.AccountRepository;
import io.wisoft.foodie.project.handler.exception.AccountNotFoundException;
import io.wisoft.foodie.project.web.dto.AccountDto;
import io.wisoft.foodie.project.web.dto.req.SignInRequest;
import io.wisoft.foodie.project.web.dto.req.SignUpRequest;
import io.wisoft.foodie.project.web.dto.res.SignInResponse;
import io.wisoft.foodie.project.web.dto.res.SignUpResponse;
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

        AccountDto accountDto = new AccountDto(
                request.getEmail(),
                request.getPassword(),
                request.getNickname()
        );

        accountDto = accountRepository.save(Account.from(accountDto))
                .toDomain();

        return new SignUpResponse(accountDto.getId());

    }

    @Transactional(readOnly = true)
    public SignInResponse signIn(final SignInRequest request){

        final AccountDto accountDto = accountRepository.findByEmail(request.getEmail())
                .orElseThrow(
                        () -> new AccountNotFoundException(request.getEmail() + "에 해당하는 사용자를 찾을 수 없습니다.")
                )
                .toDomain();

        accountDto.checkPassword(request.getPassword());

        return new SignInResponse(accountDto.getId());

    }

}
