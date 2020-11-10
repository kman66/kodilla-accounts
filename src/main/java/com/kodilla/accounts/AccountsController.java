package com.kodilla.accounts;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RefreshScope
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/accounts")
@RequiredArgsConstructor
public class AccountsController {
	@Value("${application.allow-get-accounts}")
	private boolean allowGetAccounts;
	@Autowired
	private AccountsService accountsService;

	@GetMapping
	public GetAccountsResponse getAccounts(@RequestParam("customerId") Long customerId) {
		if (!allowGetAccounts) {
			log.info("Getting accounts is disabled");
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Getting accounts is disabled");
		}
		List<AccountDTO> accounts = accountsService.getAccountsForCustomer(customerId);
		return GetAccountsResponse.of(accounts);
	}
}
