package org.matt.dev.codes.service;

import org.matt.dev.codes.model.CasaAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CasaAccountService {
    Flux<CasaAccount> findEligibleAndNonBlacklistedAccounts(String bbn, String accountType);
    Mono<Void> deleteCasaAccountByBbnAndAccountNumber(String bbn, String accountNumber);
    Flux<CasaAccount> findAllAccounts();
    Mono<CasaAccount> updateCasaAccount(CasaAccount account);
    Mono<CasaAccount> createCasaAccount(CasaAccount account);
}