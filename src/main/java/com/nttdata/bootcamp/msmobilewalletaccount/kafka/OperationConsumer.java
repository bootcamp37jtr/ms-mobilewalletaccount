
package com.nttdata.bootcamp.msmobilewalletaccount.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.nttdata.bootcamp.msmobilewalletaccount.kafka.model.MobileWalletOperation;
import com.nttdata.bootcamp.msmobilewalletaccount.kafka.model.OperationExternal;
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
	
	@KafkaListener(topics = "${kafka.topic.yunki.name}")
	public void replicateOperationListen(@Payload MobileWalletOperation operation) {
		log.debug(" replicateOperation Message received : {} ", operation);
		replicateOperation(operation).block();
	}

	private Mono<MobileWalletAccount> updateBalance(MobileWalletOperation operation) {
		return mobileWalletAccountService.findById(operation.getIdaccount()).flatMap(aco -> {
			log.info("updating account ...",aco.getCellphoneNumber());
			aco.setAvailableBalance(aco.getAvailableBalance() + operation.getAmount());
			return mobileWalletAccountService.saveAccount(Mono.just(aco));
		});
	}
	
	private Mono<MobileWalletAccount> replicateOperation(MobileWalletOperation operation) {
		return mobileWalletAccountService.findByCellphoneNumber(operation.getIdaccount()).flatMap(mwa -> {
			log.info("updating mobile wallet account");
			if("R".equals(operation.getTypeAction())) {
				mwa.setAvailableBalance(mwa.getAvailableBalance() - operation.getAmount());
			} else {
				mwa.setAvailableBalance(mwa.getAvailableBalance() + operation.getAmount());
			}
			return mobileWalletAccountService.saveAccount(Mono.just(mwa));
		});
	}
}
