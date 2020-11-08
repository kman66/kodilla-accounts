package com.kodilla.microservices.accounts;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
	private long id;

	@Column(name = "customer_id", nullable = false)
	private long customerId;

	@Column(name = "nrb")
	private String nrb;

	@Column(name = "currency")
	private String currency;

	@Column(name = "available_funds")
	private BigDecimal availableFunds;
}
