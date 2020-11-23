package com.kodilla.accounts;

import com.kodilla.commons.TransferMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
public class TransferRealizationService {
	@Autowired
	private AccountsService accountsService;

	@Transactional(rollbackFor = Exception.class)
	public void performTransfer(TransferMessage transferMessage) {
		BigDecimal amount = transferMessage.getTransfer().getAmount();

		String senderNrb = transferMessage.getTransfer().getSenderAccount();
		AccountDTO senderAccountDTO = accountsService.getAccountForNrb(senderNrb);
		log.info("Updating sender account: {}", senderAccountDTO);
		BigDecimal senderAmountAfterTransfer = senderAccountDTO.getAvailableFunds().add(amount.negate());
		AccountDTO updatedSenderAccountDTO = accountsService.saveAccount(new AccountDTO(
				senderAccountDTO.getId(),
				senderAccountDTO.getCustomerId(),
				senderAccountDTO.getNrb(),
				senderAccountDTO.getCurrency(),
				senderAmountAfterTransfer
		));
		log.info("Sender account has been updated: {}", updatedSenderAccountDTO);

		String recipientNrb = transferMessage.getTransfer().getRecipientAccount();
		AccountDTO recipientAccountDTO = accountsService.getAccountForNrb(recipientNrb);
		if (recipientAccountDTO != null) {
			log.info("Updating recipient account: {}", recipientAccountDTO);
			BigDecimal recipientAmountAfterTransfer = recipientAccountDTO.getAvailableFunds().add(amount);
			AccountDTO updatedRecipientAccountDTO = accountsService.saveAccount(new AccountDTO(
					recipientAccountDTO.getId(),
					recipientAccountDTO.getCustomerId(),
					recipientAccountDTO.getNrb(),
					recipientAccountDTO.getCurrency(),
					recipientAmountAfterTransfer
			));
			log.info("Recipient account has been updated: {}", updatedRecipientAccountDTO);
		}
	}
}
