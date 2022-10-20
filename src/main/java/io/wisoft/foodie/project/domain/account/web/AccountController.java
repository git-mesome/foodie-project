package io.wisoft.foodie.project.domain.account.web;


import io.wisoft.foodie.project.domain.account.web.dto.req.UpdateAccountRequest;
import io.wisoft.foodie.project.domain.account.application.AccountService;
import io.wisoft.foodie.project.domain.account.web.dto.res.DeleteAccountResponse;
import io.wisoft.foodie.project.domain.account.web.dto.res.FindAccountInfoResponse;
import io.wisoft.foodie.project.domain.account.web.dto.res.UpdateAccountResponse;
import io.wisoft.foodie.project.global.resolver.AccountIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindAccountInfoResponse> findAccountById(@PathVariable("id") @Valid final Long accountId) {
        return ResponseEntity.ok(
                accountService.findById(accountId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateAccountResponse> update(
            @PathVariable("id") @AccountIdentifier final Long accountId,
            @RequestBody @Valid final UpdateAccountRequest updateAccountRequest) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.update(updateAccountRequest, accountId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteAccountResponse> delete(@PathVariable("id") @Valid final Long accountId) {
        return ResponseEntity.ok(
                accountService.delete(accountId)
        );
    }

}
