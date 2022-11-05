package com.nttdata.bootcamp.msmobilewalletaccount.service;

import com.nttdata.bootcamp.msmobilewalletaccount.model.MobileWalletAccount;

import reactor.core.publisher.Mono;

public interface MobileWalletAccountService {
	Mono<MobileWalletAccount> saveAccount(Mono<MobileWalletAccount> mobileWalletAccount);
	
	Mono<MobileWalletAccount> findById(String id);
	
	Mono<MobileWalletAccount> findByCellphoneNumber(String cellphoneNumber);
}
