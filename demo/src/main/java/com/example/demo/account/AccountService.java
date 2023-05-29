package com.example.demo.account;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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


    public void addNewAccount(Account account) throws IllegalAccessException {
        Optional<Account> accountOptional = accountRepository.findAccountByEmail(account.getEmail());
        if(accountOptional.isPresent()){
            throw new IllegalAccessException("email taken");
        }
        accountRepository.save(account);
    }

    public void deleteAccount(Long accountId) throws IllegalAccessException {
        boolean exists = accountRepository.existsById(accountId);
        if(!exists){
            throw new IllegalAccessException("account with id "+ accountId + " does not exists");
        }
        accountRepository.deleteById(accountId);
    }

    @Transactional
    public void updateAccount(Long accountId, String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber, String email, String role,String password) {
    Account account = accountRepository.findById(accountId).orElseThrow(()-> new IllegalStateException("account with id "+ accountId +" does not exist"));
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
            Optional<Account> accountOptional = accountRepository.findAccountByEmail(email);
            if(accountOptional.isPresent()){
                throw new IllegalStateException("email already taken");
            }
            account.setEmail(email);
        }
    }


    public Optional<Account> findAccountByEmail(String email) {
        return accountRepository.findAccountByEmail(email);
    }
}
