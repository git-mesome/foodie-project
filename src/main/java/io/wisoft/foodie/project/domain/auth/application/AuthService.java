package io.wisoft.foodie.project.domain.auth.application;

import io.wisoft.foodie.project.domain.auth.web.ErrorCode;
import io.wisoft.foodie.project.domain.auth.exception.AccountException;
import io.wisoft.foodie.project.domain.account.persistance.Account;
import io.wisoft.foodie.project.domain.account.persistance.AccountRepository;
import io.wisoft.foodie.project.domain.auth.web.dto.req.FindByOauthIdRequest;
import io.wisoft.foodie.project.domain.auth.web.dto.req.SignUpRequest;
import io.wisoft.foodie.project.domain.auth.web.dto.res.SignUpResponse;
import io.wisoft.foodie.project.domain.auth.web.dto.res.SignInResponse;
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
    public SignUpResponse signUp(final SignUpRequest request) {

        final Account account = accountRepository.save(new Account(
                request.oauthId(),
                request.email(),
                request.profileImagePath(),
                request.nickname(),
                request.phoneNumber()
        ));

        return new SignUpResponse(
                account.getId(),
                account.getNickname(),
                account.getProfileImagePath());

    }

    @Transactional(readOnly = true)
    public SignInResponse signIn(final FindByOauthIdRequest request) {

        final Account account = accountRepository.findByOauthId(request.oauthId())
                .orElseThrow(() -> new AccountException(ErrorCode.NOT_FOUND_ACCOUNT));

        return new SignInResponse(
                account.getId(),
                account.getNickname(),
                account.getProfileImagePath());

    }

}
