package com.example.demo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> RegisterNewAccount(@RequestBody Account account) {
        return accountService.addNewAccount(account);
    }

    @DeleteMapping(path ="{accountId}" )
    public ResponseEntity<?> deleteAccount(@PathVariable("accountId") Long accountId) {
        return accountService.deleteAccount(accountId);
    }

    @PutMapping(path="{accountId}")
    public ResponseEntity<?> updateAccount(
            @PathVariable("accountId") Long accountId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) LocalDate dateOfBirth,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String password){
        return accountService.updateAccount(accountId,firstName,lastName,dateOfBirth,phoneNumber,email,role,password);

    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam(name="email")String email,@RequestParam(name="password")String password) {

        // Find the user by email
        Optional<Account> accountOptional = accountService.findAccountByEmail(email);
        if (!accountOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou mot de passe invalide");
        }

        // Check the password
        if (!accountOptional.get().getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou mot de passe invalide");
        }

        // Authentication successful
        return ResponseEntity.status(HttpStatus.OK).body(accountOptional.get().getRole());
    }

}