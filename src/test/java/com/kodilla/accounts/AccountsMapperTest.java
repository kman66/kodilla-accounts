package com.kodilla.accounts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountsMapperTest {
	@Autowired
	AccountsMapper accountsMapper;

	@Test
	public void shouldMapToAccountDTO() {
		// given
		Account account = new Account(
				1234,
				1,
				"1234567890",
				"PLN",
				BigDecimal.valueOf(4354.23)
		);
		// when
		AccountDTO accountDTO = accountsMapper.mapToAccountDTO(account);
		// then
		assertNotNull(accountDTO);
		assertTrue(accountDTO instanceof AccountDTO);
		assertEquals(1234, accountDTO.getId(), 0);
		assertEquals("1234567890", accountDTO.getNrb());
		assertEquals("PLN", accountDTO.getCurrency());
		assertEquals(BigDecimal.valueOf(4354.23), accountDTO.getAvailableFunds());
	}

}