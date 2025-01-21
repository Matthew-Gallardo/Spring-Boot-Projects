package org.matt.dev.codes.service.impl;

import org.matt.dev.codes.model.CasaAccount;
import org.matt.dev.codes.repository.CasaAccountRepository;
import org.matt.dev.codes.service.CasaAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CasaAccountServiceImpl implements CasaAccountService {

    @Autowired
    private CasaAccountRepository casaAccountRepository;

    @Override
    public Flux<CasaAccount> findEligibleAndNonBlacklistedAccounts(String bbn, String accountType) {
        return casaAccountRepository.findAll()
            .filter(account -> account.getCustomerBetterBankingNumber().equals(bbn))
            .filter(account -> account.getAccountTypeCode().equals(accountType))
            .filter(account -> account.getEligibilityFlag() == 1 && account.getBlacklistFlag() == 0);
    }

    @Override
    public Flux<CasaAccount> findAllAccounts() {
        return casaAccountRepository.findAll();
    }



    @Override
    public Mono<Void> deleteCasaAccountByBbnAndAccountNumber(String bbn, String accountNumber) {
        return casaAccountRepository.findByCustomerBetterBankingNumberAndAccountNumber(bbn, accountNumber)
            .flatMap(casaAccount -> casaAccountRepository.delete(casaAccount));
    }

    @Override
    public Mono<CasaAccount> updateCasaAccount(CasaAccount updatedAccount) {
        return casaAccountRepository.findByCustomerBetterBankingNumberAndAccountNumber(
            updatedAccount.getCustomerBetterBankingNumber(), updatedAccount.getAccountNumber())
            .flatMap(existingAccount -> casaAccountRepository.save(updatedAccount));
    }

    @Override
    public Mono<CasaAccount> createCasaAccount(CasaAccount account) {
        return casaAccountRepository.save(account);
    }


    @Override
    public Mono<CasaAccount> findAccountByBbn(String bbn) {
        return casaAccountRepository.findById(bbn);
    }
}