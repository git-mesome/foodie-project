package io.wisoft.foodie.project.domain.account.application;

import io.wisoft.foodie.project.domain.account.persistance.Account;
import io.wisoft.foodie.project.domain.account.persistance.AccountRepository;
import io.wisoft.foodie.project.domain.account.web.dto.req.UpdateAccountRequest;
import io.wisoft.foodie.project.domain.account.web.dto.res.DeleteAccountResponse;
import io.wisoft.foodie.project.domain.account.web.dto.res.FindAccountInfoResponse;
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

    @Transactional(readOnly = true)
    public FindAccountInfoResponse findById(final Long accountId) {

        final Account account = accountRepository.findById(accountId)
                .orElseThrow(
                        () -> new AccountNotFoundException(accountId + "에 해당하는 사용자가 없습니다.")
                );

        return new FindAccountInfoResponse(
                account.getId(),
                account.getNickname(),
                account.getEmail(),
                account.getProfileImagePath(),
                account.getPhoneNumber(),
                account.getGrade(),
                account.getSiDo(),
                account.getSiGunGu(),
                account.getEupMyeonDong()
        );

    }

    public UpdateAccountResponse update(final UpdateAccountRequest request, final Long id) {

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
    public DeleteAccountResponse delete(final Long id) {
        final Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("없는 사용자 입니다."));

        accountRepository.deleteById(account.getId());

        return new DeleteAccountResponse(account.getId());
    }

}
