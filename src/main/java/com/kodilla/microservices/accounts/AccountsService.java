package com.kodilla.microservices.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountsService {
	@Autowired
	private AccountsRepository accountsRepository;
	@Autowired
	private AccountsMapper accountsMapper;

	public List<AccountDTO> getAccountsForCustomer(Long customerId) {
		return accountsRepository.findByCustomerId(customerId)
				.stream()
				.map(account -> accountsMapper.mapToAccountDTO(account))
				.collect(Collectors.toList());
	}
}
