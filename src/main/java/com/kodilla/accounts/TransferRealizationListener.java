package com.kodilla.accounts;

import com.kodilla.commons.TransferMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferRealizationListener {

	private final TransferRealizationService transferRealizationService;

	@KafkaListener(topics = "transfers-realization")
	public void consume(@Payload TransferMessage transferMessage) {
		log.info("Consumed transferMessage: {}", transferMessage);
		transferRealizationService.performTransfer(transferMessage);
	}
}
