package org.matt.dev.codes.repository;

import org.matt.dev.codes.model.CasaAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CasaAccountRepository extends ReactiveMongoRepository<CasaAccount, String> {
    Mono<CasaAccount> findByCustomerBetterBankingNumberAndAccountNumber(String customerBetterBankingNumber, String accountNumber);
}