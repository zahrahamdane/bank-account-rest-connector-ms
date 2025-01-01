package com.zaradev.bank_account_service;

import java.util.Date;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.zaradev.bank_account_service.entities.BankAccount;
import com.zaradev.bank_account_service.enums.AccountType;
import com.zaradev.bank_account_service.repositories.BankAccountRepository;

@SpringBootApplication
public class BankAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BankAccountRepository bankAccountRepository) {
		return args -> {
			for (int i = 0; i < 10; i++) {
				BankAccount bankAccount = BankAccount.builder()
						.id(UUID.randomUUID().toString())
						.type(Math.random() > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
						.balance(10000 + Math.random() * 90000)
						.currency("MAD")
						.createdAt(new Date())
						.build();
				bankAccountRepository.save(bankAccount);
			}
		};
	}


}
