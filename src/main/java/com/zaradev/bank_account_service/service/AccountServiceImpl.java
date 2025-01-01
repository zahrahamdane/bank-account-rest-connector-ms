package com.zaradev.bank_account_service.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zaradev.bank_account_service.dto.BankAccountRequestDto;
import com.zaradev.bank_account_service.dto.BankAccountResponseDto;
import com.zaradev.bank_account_service.entities.BankAccount;
import com.zaradev.bank_account_service.mappers.AccountMapper;
import com.zaradev.bank_account_service.repositories.BankAccountRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private BankAccountRepository bankAccountRepository;
    private AccountMapper accountMapper;
    @Override
    public BankAccountResponseDto AddAccount(BankAccountRequestDto bankAccountDto) {
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountDto.getBalance())
                .type(bankAccountDto.getType())
                .currency(bankAccountDto.getCurrency())
                .build();
        BankAccount saveBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDto bankAccountResponseDto =  accountMapper.fromBankAccount(saveBankAccount);
        return bankAccountResponseDto;
    }
}
