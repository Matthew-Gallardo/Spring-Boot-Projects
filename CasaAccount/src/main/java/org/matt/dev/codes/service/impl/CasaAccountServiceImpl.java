package org.matt.dev.codes.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.matt.dev.codes.model.CasaAccount;
import org.matt.dev.codes.model.ProductDisplay;
import org.matt.dev.codes.model.SpecialConditionDetail;
import org.matt.dev.codes.service.CasaAccountService;
import org.springframework.stereotype.Service;

@Service
public class CasaAccountServiceImpl implements CasaAccountService {

    private List<CasaAccount> accounts = new ArrayList<>(Arrays.asList(
        new CasaAccount("123", "1", "Account 1", "Product 1", "PTC1", "Nick 1", "ATC1", "Description 1", new BigDecimal("1000.00"), new BigDecimal("800.00"), "PHP", "2021-01-01", "Branch 1", "Ownership 1", "SWIFT1", 1, "Active", 
        		Arrays.asList(new SpecialConditionDetail("SC1", "Description SC1")), 
        		new ProductDisplay("Image1", "Color1", "Border1", "ActionColor1"), false, 0),
        new CasaAccount("456", "2", "Account 2", "Product 2", "PTC2", "Nick 2", "ATC2", "Description 2", new BigDecimal("2000.00"), new BigDecimal("1800.00"), "PHP", "2021-02-01", "Branch 2", "Ownership 2", "SWIFT2", 1, "Active", 
        		Arrays.asList(new SpecialConditionDetail("SC2", "Description SC2")), 
        		new ProductDisplay("Image2", "Color2", "Border2", "ActionColor2"), false, 0),
        new CasaAccount("769", "3", "Account 3", "Product 3", "PTC3", "Nick 3", "ATC3", "Description 3", new BigDecimal("3000.00"), new BigDecimal("2800.00"), "PHP", "2021-03-01", "Branch 3", "Ownership 3", "SWIFT3", 0, "Inactive", 
        		Arrays.asList(new SpecialConditionDetail("SC3", "Description SC3")), 
        		new ProductDisplay("Image3", "Color3", "Border3", "ActionColor3"), true, 1)
    ));

    @Override
    public List<CasaAccount> findEligibleAndNonBlacklistedAccounts(String bbn, String accountType) {
        return accounts.stream()
            .filter(account -> account.getCustomerBetterBankingNumber().equals(bbn))
            .filter(account -> account.getAccountTypeCode().equals(accountType))
            .filter(account -> account.getEligibilityFlag() == 1 && account.getBlacklistFlag() == 0)
            .collect(Collectors.toList());
    }

    @Override
    public List<CasaAccount> findAllAccounts() {
        return new ArrayList<>(accounts);
    }

    @Override
    public void deleteCasaAccountByBbnAndAccountNumber(String bbn, String accountNumber) {
        accounts = accounts.stream()
            .filter(account -> !(account.getCustomerBetterBankingNumber().equals(bbn) && account.getAccountNumber().equals(accountNumber)))
            .collect(Collectors.toList());
    }

    @Override
    public CasaAccount updateCasaAccount(CasaAccount updatedAccount) {
        for (int i = 0; i < accounts.size(); i++) {
            CasaAccount account = accounts.get(i);
            if (account.getCustomerBetterBankingNumber().equals(updatedAccount.getCustomerBetterBankingNumber()) &&
                account.getAccountNumber().equals(updatedAccount.getAccountNumber())) {
                accounts.set(i, updatedAccount);
                return updatedAccount;
            }
        }
        return null;
    }
}
