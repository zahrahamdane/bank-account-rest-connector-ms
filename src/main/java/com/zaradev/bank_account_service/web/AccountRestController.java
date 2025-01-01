package com.zaradev.bank_account_service.web;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zaradev.bank_account_service.dto.BankAccountRequestDto;
import com.zaradev.bank_account_service.dto.BankAccountResponseDto;
import com.zaradev.bank_account_service.entities.BankAccount;
import com.zaradev.bank_account_service.mappers.AccountMapper;
import com.zaradev.bank_account_service.repositories.BankAccountRepository;
import com.zaradev.bank_account_service.service.AccountService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AccountRestController {

    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;

    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts() {
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Bank account with id %s not found", id)));
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDto save(@RequestBody BankAccountRequestDto requestDTO) {
        return accountService.AddAccount(requestDTO);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id, @RequestBody BankAccount bankAccount) {
        BankAccount account = bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Bank account with id %s not found", id)));
        if(bankAccount.getBalance() != null) account.setBalance(bankAccount.getBalance());
        if(bankAccount.getCurrency() != null) account.setCurrency(bankAccount.getCurrency());
        if(bankAccount.getCreatedAt() != null) account.setCreatedAt(bankAccount.getCreatedAt());
        if(bankAccount.getType() != null) account.setType(bankAccount.getType());

        return bankAccountRepository.save(account);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void delateAccount(@PathVariable String id) {
        bankAccountRepository.deleteById(id);
    }
}
