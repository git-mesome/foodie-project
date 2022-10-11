package io.wisoft.foodie.project.domain.account.application;

import io.wisoft.foodie.project.domain.account.persistance.Account;
import io.wisoft.foodie.project.domain.account.persistance.AccountRepository;
import io.wisoft.foodie.project.domain.account.web.dto.req.UpdateAccountRequest;
import io.wisoft.foodie.project.domain.account.web.dto.res.UpdateAccountResponse;
import io.wisoft.foodie.project.exception.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public UpdateAccountResponse updateAccountInfo(final UpdateAccountRequest request, final Long id) {

        final Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("없는 사용자 입니다."));

        account.update(request.nickname(),
                request.phoneNumber(),
                request.siDo(),
                request.siGunGu(),
                request.eupMyeonDong());

        accountRepository.save(account);

        return new UpdateAccountResponse(account.getId());

    }

    @Transactional
    public UpdateAccountResponse deleteAccount(final Long id) {
        final Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("없는 사용자 입니다."));

        accountRepository.deleteById(account.getId());

        return new UpdateAccountResponse(account.getId());
    }

//    @Transactional(readOnly = true)
//    public FindAccountListResponse findAccountList() {
//
//        return FindAccountListResponse.builder()
//                .accountList(accountRepository.findAll().stream().map().toList())
//                .build();
//
//
//    }

//    @Transactional(readOnly = true)
//    public FindAccountResponse findAccountById(final Long accountId) {
//
//        final Account account = accountRepository.findById(accountId)
//                .orElseThrow(
//                        () -> new AccountNotFoundException(accountId + "에 해당하는 사용자가 없습니다.")
//                )
//                .toDomain();
//
//        return new FindAccountResponse(
//                account.getEmail(),
//                account.getNickName()
//        );
//
//    }


}
