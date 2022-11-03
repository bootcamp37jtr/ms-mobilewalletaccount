package com.nttdata.bootcamp.msmobilewalletaccount.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.nttdata.bootcamp.msmobilewalletaccount.kafka.model.MobileWalletOperation;
import com.nttdata.bootcamp.msmobilewalletaccount.model.MobileWalletAccount;
import com.nttdata.bootcamp.msmobilewalletaccount.service.MobileWalletAccountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class OperationConsumer {

	@Autowired
	MobileWalletAccountService mobileWalletAccountService;

	@KafkaListener(topics = "${kafka.topic.balance.name}")
	public void listener(@Payload MobileWalletOperation operation) {
		log.debug("Message received : {} ", operation);
		updateBalance(operation).block();
	}

	private Mono<MobileWalletAccount> updateBalance(MobileWalletOperation operation) {
		return mobileWalletAccountService.findById(operation.getId()).flatMap(aco -> {
			aco.setAvailableBalance(aco.getAvailableBalance() + operation.getAmount());
			return mobileWalletAccountService.saveAccount(Mono.just(aco));
		});
	}
}
