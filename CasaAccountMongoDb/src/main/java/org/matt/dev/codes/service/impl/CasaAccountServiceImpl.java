package org.matt.dev.codes.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.matt.dev.codes.model.CasaAccount;
import org.matt.dev.codes.repository.CasaAccountRepository;
import org.matt.dev.codes.service.CasaAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CasaAccountServiceImpl implements CasaAccountService {

    @Autowired
    private CasaAccountRepository casaAccountRepository;

    @Override
    public List<CasaAccount> findEligibleAndNonBlacklistedAccounts(String bbn, String accountType) {
        return casaAccountRepository.findAll().stream()
            .filter(account -> account.getCustomerBetterBankingNumber().equals(bbn))
            .filter(account -> account.getAccountTypeCode().equals(accountType))
            .filter(account -> account.getEligibilityFlag() == 1 && account.getBlacklistFlag() == 0)
            .collect(Collectors.toList());
    }

    @Override
    public List<CasaAccount> findAllAccounts() {
        return casaAccountRepository.findAll();
    }

    @Override
    public void deleteCasaAccountByBbnAndAccountNumber(String bbn, String accountNumber) {
        Optional<CasaAccount> account = casaAccountRepository.findByCustomerBetterBankingNumberAndAccountNumber(bbn, accountNumber);
        account.ifPresent(casaAccountRepository::delete);
    }

    @Override
    public CasaAccount updateCasaAccount(CasaAccount updatedAccount) {
        Optional<CasaAccount> existingAccount = casaAccountRepository.findByCustomerBetterBankingNumberAndAccountNumber(
            updatedAccount.getCustomerBetterBankingNumber(), updatedAccount.getAccountNumber());
        if (existingAccount.isPresent()) {
            return casaAccountRepository.save(updatedAccount);
        }
        return null;
    }

    @Override
    public CasaAccount createCasaAccount(CasaAccount account) {
        return casaAccountRepository.save(account);
    }

}