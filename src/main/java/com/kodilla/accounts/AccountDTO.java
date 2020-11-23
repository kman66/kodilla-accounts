package com.kodilla.accounts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
	private Long id;
	private Long customerId;
	private String nrb;
	private String currency;
	private BigDecimal availableFunds;
}
