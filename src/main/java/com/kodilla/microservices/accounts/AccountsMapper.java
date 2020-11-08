package com.kodilla.microservices.accounts;

import org.springframework.stereotype.Component;

@Component
public class AccountsMapper {

	public AccountDTO mapToAccountDTO(final Account account) {
		return new AccountDTO(account.getId(), account.getNrb(), account.getCurrency(),
				account.getAvailableFunds()
		);
	}
}
