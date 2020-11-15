package com.kodilla.accounts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "accounts")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "customer_id", nullable = false)
	private Long customerId;

	@Column(name = "nrb")
	private String nrb;

	@Column(name = "currency")
	private String currency;

	@Column(name = "available_funds")
	private BigDecimal availableFunds;
}
