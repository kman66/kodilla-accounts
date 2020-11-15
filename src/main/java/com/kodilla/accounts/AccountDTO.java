package com.kodilla.accounts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class AccountDTO {
	private Long id;
	private String nrb;
	private String currency;
	private BigDecimal availableFunds;
}
