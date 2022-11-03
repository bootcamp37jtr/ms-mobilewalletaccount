package com.nttdata.bootcamp.msmobilewalletaccount.infraestructure;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.bootcamp.msmobilewalletaccount.model.MobileWalletAccount;

@Repository
public interface MobileWalletAccountRepository extends ReactiveMongoRepository<MobileWalletAccount, String> {

}
