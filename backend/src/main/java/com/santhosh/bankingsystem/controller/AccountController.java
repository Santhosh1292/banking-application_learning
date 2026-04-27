package com.santhosh.bankingsystem.controller;

import com.santhosh.bankingsystem.entity.Account;
import com.santhosh.bankingsystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.santhosh.bankingsystem.dto.AmountRequest;
import com.santhosh.bankingsystem.dto.TransferRequest;
import java.util.*;
@RestController
@RequestMapping("/accounts")
@CrossOrigin(origins = "*")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }
    @PutMapping("/{id}/deposit")
    public Account deposit(@PathVariable Long id, @RequestBody AmountRequest request) {
        return accountService.deposit(id, request.getAmount());
    }
    @PutMapping("/{id}/withdraw")
    public Account withdraw(@PathVariable Long id, @RequestBody AmountRequest request) {
        return accountService.withdraw(id, request.getAmount());
    }
    @PutMapping("/transfer")
    public String transfer(@RequestBody TransferRequest request) {
        accountService.transfer(
                request.getFromAccountId(),
                request.getToAccountId(),
                request.getAmount()
        );
        return "Transfer successful";
    }
    @PostMapping("/bulk")
    public List<Account> createAccounts(@RequestBody List<Account> accounts) {
        return accountService.createAccounts(accounts);
    }
}