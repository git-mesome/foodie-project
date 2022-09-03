package io.wisoft.foodie.project.service;

import io.wisoft.foodie.project.domain.account.Account;
import io.wisoft.foodie.project.domain.account.AccountRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Long join(Account account){

        validateDuplicateAccount(account);
        accountRepository.save(account);

        return account.getId();
    }

    private void validateDuplicateAccount(@NotNull Account account){

        List<Account> findAccounts = accountRepository.findByEmail(account.getEmail());

        if (!findAccounts.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

    public List<Account> findAccounts(){
        return accountRepository.findAll();
    }

    public Account findOnAccount(final Long accountId){

        return accountRepository.getReferenceById(accountId);
    }

//    public FindAccountResponse findAccountById(final Long accountId) {
//
//        final Account account = accountRepository.findById(accountId)
//                .orElseThrow(
//                        () -> new AccountNotFoundException(accountId + "에 해당하는 사용자가 없습니다.")
//                ).toDomain();
//
//        return new FindAccountResponse(
//                account.getName(),
//                account.getEmail()
//        );
//
//    }


}
