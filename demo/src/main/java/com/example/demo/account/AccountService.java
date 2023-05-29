package com.example.demo.account;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountService {
    private AccountRepository accountRepository;
//    private final List<Account> accounts;


    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
//        this.accounts = accounts;
    }


    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }


    public ResponseEntity<?> addNewAccount(Account account) {
        Optional<Account> accountOptional = accountRepository.findAccountByEmail(account.getEmail());
        if(accountOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("e-mail déja pris");
        }
        accountRepository.save(account);
        return ResponseEntity.ok("compte enregistré avec succès");
    }

    public ResponseEntity<?> deleteAccount(Long accountId){
        boolean exists = accountRepository.existsById(accountId);
        if(!exists){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Le compte n'existe pas");
        }
        accountRepository.deleteById(accountId);
        return ResponseEntity.ok("compte supprimé avec succès");

    }

    @Transactional
    public ResponseEntity<?> updateAccount(Long accountId, String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber, String email, String role,String password) {
    Optional<Account>  accountOptional = accountRepository.findById(accountId);

    Account account;

    if(accountOptional.isPresent()) {
        account = accountOptional.get();
    }else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("le compte n'existe pas");
    }

    if(firstName != null && firstName.length() > 0 && !Objects.equals(account.getFirstName(),firstName)){
        account.setFirstName(firstName);
    }
        if(lastName != null && lastName.length() > 0 && !Objects.equals(account.getLastName(),lastName)){
            account.setLastName(lastName);
        }
        if(phoneNumber != null && phoneNumber.length() > 0 && !Objects.equals(account.getPhoneNumber(),phoneNumber)){
            account.setPhoneNumber(phoneNumber);
        }
        if(role != null && role.length() > 0 && !Objects.equals(account.getRole(),role)){
            account.setRole(role);
        }
        if(password != null && password.length() > 0 && !Objects.equals(account.getPassword(),password)){
            account.setPassword(password);
        }


        if (dateOfBirth != null && !Objects.equals(account.getDateOfBirth(), dateOfBirth)) {
            account.setDateOfBirth(dateOfBirth);
        }

        if(email != null && email.length() > 0 && !Objects.equals(account.getEmail(),email)){
            Optional<Account> accountOptional1 = accountRepository.findAccountByEmail(email);
            if(accountOptional1.isPresent()){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("email déja existe");
            }
            account.setEmail(email);
        }
        return ResponseEntity.ok("Le compte a été modifié avec succès");
    }


    public Optional<Account> findAccountByEmail(String email) {
        return accountRepository.findAccountByEmail(email);
    }
}