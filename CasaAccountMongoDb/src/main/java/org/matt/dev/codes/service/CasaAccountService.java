package org.matt.dev.codes.service;

import java.util.List;

import org.matt.dev.codes.model.CasaAccount;

public interface CasaAccountService {
    List<CasaAccount> findEligibleAndNonBlacklistedAccounts(String bbn, String accountType);
    void deleteCasaAccountByBbnAndAccountNumber(String bbn, String accountNumber);
    List<CasaAccount> findAllAccounts();
    CasaAccount updateCasaAccount(CasaAccount account);
    CasaAccount createCasaAccount(CasaAccount account);
    
    
    
    
    
}