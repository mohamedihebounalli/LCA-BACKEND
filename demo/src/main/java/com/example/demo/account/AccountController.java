package com.example.demo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @PostMapping
    public void RegisterNewAccount(@RequestBody Account account) throws IllegalAccessException {
        accountService.addNewAccount(account);
    }

    @DeleteMapping(path ="{accountId}" )
    public void deleteAccount(@PathVariable("accountId") Long accountId) throws IllegalAccessException {
        accountService.deleteAccount(accountId);
    }

    @PutMapping(path="{accountId}")
    public void updateAccount(
            @PathVariable("accountId") Long accountId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) Date dateOfBirth,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String role){
        accountService.updateAccount(accountId,firstName,lastName,dateOfBirth,phoneNumber,email,role);
    }

}