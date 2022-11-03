package com.nttdata.bootcamp.msmobilewalletaccount.kafka.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MobileWalletOperation {
	
	@Id
	private String id;
	private String idaccount;
	private String typeAction;  // tipo de operacion ABONO, RETIRO
	private Double amount;
	private String idaccountOriDest;
	
}
