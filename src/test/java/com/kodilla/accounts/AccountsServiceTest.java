package com.kodilla.accounts;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class AccountsServiceTest {
	@InjectMocks
	AccountsService accountsService;
	@Mock
	AccountsRepository accountsRepository;
	@Mock
	AccountsMapper accountsMapper;

	@Test
	public void shouldFetchAccountsForGivenCustomerId() {
		// given
		Long customerId = 1421L;
		Account account = new Account(
				1234L,
				customerId,
				"1234567890",
				"PLN",
				BigDecimal.valueOf(4354.23)
		);
		AccountDTO accountDTO = new AccountDTO(
				1234L,
				customerId,
				"1234567890",
				"PLN",
				BigDecimal.valueOf(4354.23)
		);
		List<Account> accounts = List.of(account);
		Mockito.when(accountsRepository.findByCustomerId(customerId)).thenReturn(accounts);
		Mockito.when(accountsMapper.mapToAccountDTO(account)).thenReturn(accountDTO);
		// when
		List<AccountDTO> fetchedAccounts = accountsService.getAccountsForCustomer(customerId);
		// then
		assertFalse(fetchedAccounts.isEmpty());
		assertEquals(1, fetchedAccounts.size());
		assertEquals(1234L, fetchedAccounts.get(0).getId(), 0);
		assertEquals("1234567890", fetchedAccounts.get(0).getNrb());
		assertEquals("PLN", fetchedAccounts.get(0).getCurrency());
		assertEquals(BigDecimal.valueOf(4354.23), fetchedAccounts.get(0).getAvailableFunds());
	}
}