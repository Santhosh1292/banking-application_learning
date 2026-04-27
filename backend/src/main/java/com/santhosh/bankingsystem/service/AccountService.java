
package com.santhosh.bankingsystem.service;

import com.santhosh.bankingsystem.entity.Account;
import com.santhosh.bankingsystem.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import com.santhosh.bankingsystem.entity.Transaction;
import com.santhosh.bankingsystem.repository.TransactionRepository;
import java.util.*;
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    // Create account
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }
    public Account deposit(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction(null, accountId, amount, "DEPOSIT");
        transactionRepository.save(transaction);

        return account;
    }
    public Account withdraw(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        if (account.getBalance() < amount) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction(accountId, null, amount, "WITHDRAW");
        transactionRepository.save(transaction);

        return account;
    }
    public List<Account> createAccounts(List<Account> accounts) {
        return accountRepository.saveAll(accounts);
    }
    @Transactional

    public void transfer(Long fromId, Long toId, double amount) {

        Account fromAccount = accountRepository.findById(fromId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sender account not found"));

        Account toAccount = accountRepository.findById(toId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receiver account not found"));

        if (fromAccount.getBalance() < amount) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient balance");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction transaction = new Transaction(fromId, toId, amount, "TRANSFER");
        transactionRepository.save(transaction);
    }
}