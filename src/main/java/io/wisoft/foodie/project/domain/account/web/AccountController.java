package io.wisoft.foodie.project.domain.account.web;


import io.wisoft.foodie.project.domain.account.web.dto.res.FindAccountListResponse;
import io.wisoft.foodie.project.domain.account.application.AccountService;
import io.wisoft.foodie.project.domain.account.web.dto.res.FindAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public ResponseEntity<FindAccountListResponse> findAccountList() {

        return ResponseEntity.ok(accountService.findAccountList());

    }

    @GetMapping("/{id}")
    public ResponseEntity<FindAccountResponse> findAccountById(@PathVariable("id") @Valid final Long accountId) {
        return ResponseEntity.ok(
                accountService.findAccountById(accountId)
        );
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<FindAccountResponse> deleteAccount(@PathVariable("id") @Valid final Long accountId){
//        return ResponseEntity.ok(
//                accountService.deleteAccount(accountId)
//        );
//    }

}
