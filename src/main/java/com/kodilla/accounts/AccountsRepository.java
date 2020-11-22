package com.kodilla.accounts;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountsRepository extends CrudRepository<Account, Long> {

	@Override
	List<Account> findAll();

	List<Account> findByCustomerId(Long customerId);
}
