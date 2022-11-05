package com.nttdata.bootcamp.msmobilewalletaccount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.msmobilewalletaccount.infraestructure.MobileWalletAccountRepository;
import com.nttdata.bootcamp.msmobilewalletaccount.model.MobileWalletAccount;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class MobileWalletAccountServiceImpl implements  MobileWalletAccountService {
	
	@Autowired
	MobileWalletAccountRepository mobileWalletAccountRepository;
	
	@Override
	public Mono<MobileWalletAccount> saveAccount(Mono<MobileWalletAccount> mobileWalletAccount) {
		log.debug("dato",mobileWalletAccount);
		return mobileWalletAccount.flatMap(mwa ->{
			if(mwa.getAvailableBalance() == null) {
				mwa.setAvailableBalance(0d);
			}
			return mobileWalletAccountRepository.save(mwa);
		});
	}

	@Override
	public Mono<MobileWalletAccount> findById(String id) {
		return mobileWalletAccountRepository.findById(id)	;
	}

	@Override
	public Mono<MobileWalletAccount> findByCellphoneNumber(String cellphoneNumber) {
		return mobileWalletAccountRepository.findByCellphoneNumber(cellphoneNumber);
	}

}
