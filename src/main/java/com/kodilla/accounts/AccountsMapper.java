package com.kodilla.accounts;

import org.springframework.stereotype.Component;

@Component
public class AccountsMapper {

	public AccountDTO mapToAccountDTO(final Account account) {
		return new AccountDTO(account.getId(), account.getCustomerId(), account.getNrb(), account.getCurrency(),
				account.getAvailableFunds()
		);
	}

	public Account mapToAccount(final AccountDTO accountDTO) {
		return new Account(accountDTO.getId(), accountDTO.getCustomerId(), accountDTO.getNrb(), accountDTO.getCurrency(),
				accountDTO.getAvailableFunds());
	}
}
