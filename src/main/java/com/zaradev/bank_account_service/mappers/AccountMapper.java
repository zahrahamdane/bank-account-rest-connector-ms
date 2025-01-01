package com.zaradev.bank_account_service.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.zaradev.bank_account_service.dto.BankAccountResponseDto;
import com.zaradev.bank_account_service.entities.BankAccount;

@Component
public class AccountMapper {
    public BankAccountResponseDto fromBankAccount(BankAccount bankAccount) {
        BankAccountResponseDto bankAccountResponseDto = new BankAccountResponseDto();
        BeanUtils.copyProperties(bankAccount, bankAccountResponseDto);
        return bankAccountResponseDto;
    }
}
