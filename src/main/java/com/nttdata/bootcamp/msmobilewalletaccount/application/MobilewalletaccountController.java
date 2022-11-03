package com.nttdata.bootcamp.msmobilewalletaccount.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.msmobilewalletaccount.model.MobileWalletAccount;
import com.nttdata.bootcamp.msmobilewalletaccount.service.MobileWalletAccountService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/mobilewalletaccount")
@RefreshScope
public class MobilewalletaccountController {
	
	@Autowired
	MobileWalletAccountService mobileWalletAccountService;
	
	@Value("${message.demo}")
	private String demoString;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<MobileWalletAccount> crear(@RequestBody MobileWalletAccount mobileWalletAccount) {
		log.info(demoString);
		return mobileWalletAccountService.saveAccount(Mono.just(mobileWalletAccount));
	}
	
}
