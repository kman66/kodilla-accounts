package com.kodilla.microservices.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/accounts")
public class AccountsController {
	@Autowired
	private AccountsService accountsService;

	@GetMapping
	public GetAccountsResponse getAccounts(@RequestParam("customerId") Long customerId) {
		List<AccountDTO> accounts = accountsService.getAccountsForCustomer(customerId);
		return GetAccountsResponse.of(accounts);
	}
}
