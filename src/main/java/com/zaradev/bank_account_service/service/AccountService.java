package com.zaradev.bank_account_service.service;

import com.zaradev.bank_account_service.dto.BankAccountRequestDto;
import com.zaradev.bank_account_service.dto.BankAccountResponseDto;
import com.zaradev.bank_account_service.entities.BankAccount;

public interface AccountService {
    BankAccountResponseDto AddAccount(BankAccountRequestDto bankAccountDto);
}
