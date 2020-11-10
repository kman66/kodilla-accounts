package com.kodilla.accounts;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountsController.class)
class AccountsControllerTest {
	@Autowired
	MockMvc mockMvc;

	@MockBean
	AccountsController accountsController;

	@Test
	public void shouldFetchAccountsForGivenCustomerId() throws Exception {
		// given
		ArrayList<AccountDTO> accounts = new ArrayList<>();
		accounts.add(
				new AccountDTO(
						1421,
						"72249000059957936727967706",
						"PLN",
						BigDecimal.valueOf(6525.11)
				));
		Mockito.when(accountsController.getAccounts(123456L)).thenReturn(GetAccountsResponse.of(accounts));
		// when & then
		mockMvc.perform(get("/v1/accounts?customerId=123456")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.aMapWithSize(1)))
				.andExpect(jsonPath("$.accounts[0].id", Matchers.is(1421)))
				.andExpect(jsonPath("$.accounts[0].nrb", Matchers.is("72249000059957936727967706")))
				.andExpect(jsonPath("$.accounts[0].currency", Matchers.is("PLN")))
				.andExpect(jsonPath("$.accounts[0].availableFunds", Matchers.is(6525.11)));
	}
}