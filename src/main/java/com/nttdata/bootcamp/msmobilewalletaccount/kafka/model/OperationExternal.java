package com.nttdata.bootcamp.msmobilewalletaccount.kafka.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationExternal {
	
	private String numberAccount;
	private String typeAction;
	private String typeOperation;  
	private Double amount;
}
