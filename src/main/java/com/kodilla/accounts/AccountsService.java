package com.kodilla.accounts;

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

	public List<AccountDTO> getAllAccounts() {
		return accountsRepository.findAll()
				.stream()
				.map(account -> accountsMapper.mapToAccountDTO(account))
				.collect(Collectors.toList());
	}

	public List<AccountDTO> getAccountsForCustomer(Long customerId) {
		return accountsRepository.findByCustomerId(customerId)
				.stream()
				.map(account -> accountsMapper.mapToAccountDTO(account))
				.collect(Collectors.toList());
	}

	public AccountDTO getAccountForNrb(String nrb) {
		return accountsMapper.mapToAccountDTO(accountsRepository.findByNrb(nrb));
	}

	public AccountDTO saveAccount(AccountDTO accountDTO) {
		return accountsMapper.mapToAccountDTO(accountsRepository.save(accountsMapper.mapToAccount(accountDTO)));
	}
}
