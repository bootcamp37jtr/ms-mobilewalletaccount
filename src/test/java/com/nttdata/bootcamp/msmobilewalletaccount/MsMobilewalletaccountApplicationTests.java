package com.nttdata.bootcamp.msmobilewalletaccount;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.nttdata.bootcamp.msmobilewalletaccount.application.MobilewalletaccountController;
import com.nttdata.bootcamp.msmobilewalletaccount.model.MobileWalletAccount;
import com.nttdata.bootcamp.msmobilewalletaccount.service.MobileWalletAccountService;

import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(MobilewalletaccountController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
class MsMobilewalletaccountApplicationTests {
	
	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private MobileWalletAccountService mobileWalletAccountService;

	@Test
	void crearTest() {
		Mono<MobileWalletAccount> mobileWalletAccount = Mono.just(new 
				MobileWalletAccount("4444", "645656", "dni","654545", "2211121","mail.gmail.com",
						4d,"555555"));
			when(mobileWalletAccountService.saveAccount(mobileWalletAccount)).thenReturn(mobileWalletAccount);
			webTestClient.post().uri("/mobilewalletaccount")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.body(Mono.just(mobileWalletAccount), MobileWalletAccount.class)
			.exchange()
			.expectStatus().isCreated();
	}

}
