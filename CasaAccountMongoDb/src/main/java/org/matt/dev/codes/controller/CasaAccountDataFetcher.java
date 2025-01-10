package org.matt.dev.codes.controller;

import org.matt.dev.codes.model.CasaAccount;
import org.matt.dev.codes.model.dto.CasaAccountInput;
import org.matt.dev.codes.model.dto.CreateCasaAccountInput;
import org.matt.dev.codes.model.dto.UpdateCasaAccountInput;
import org.matt.dev.codes.service.CasaAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CasaAccountDataFetcher {

    @Autowired
    private CasaAccountService casaAccountService;

    @QueryMapping
    public List<CasaAccount> getEligibleAndNonBlacklistedAccounts(@Argument String bbn,
                                                                  @Argument String accountType) {
        return casaAccountService.findEligibleAndNonBlacklistedAccounts(bbn, accountType);
    }

    @QueryMapping
    public List<CasaAccount> getAllAccounts() {
        return casaAccountService.findAllAccounts();
    }

    @MutationMapping
    public void deleteCasaAccountByBbnAndAccountNumber(@Argument CasaAccountInput input) {
        casaAccountService.deleteCasaAccountByBbnAndAccountNumber(input.getBbn(), input.getAccountNumber());
    }

    @MutationMapping
    public CasaAccount updateCasaAccount(@Argument UpdateCasaAccountInput input) {
        CasaAccount updatedAccount = new CasaAccount(
            input.getCustomerBetterBankingNumber(),
            input.getAccountNumber(),
            input.getAccountName(),
            input.getProductName(),
            input.getProductTypeCode(),
            input.getAccountNickName(),
            input.getAccountTypeCode(),
            input.getAccountTypeDescription(),
            input.getCurrentBalance(),
            input.getAvailableBalance(),
            input.getCurrency(),
            input.getOpeningDate(),
            input.getBranchName(),
            input.getOwnershipType(),
            input.getSwiftCode(),
            input.getEligibilityFlag(),
            input.getAccountStatus(),
            null, // Assuming specialConditionDetails and productDisplay are not updated
            null, // Assuming specialConditionDetails and productDisplay are not updated
            input.getIsHidden(),
            input.getBlacklistFlag()
        );
        return casaAccountService.updateCasaAccount(updatedAccount);
    }

    @MutationMapping
    public CasaAccount createCasaAccount(@Argument CreateCasaAccountInput input) {
        CasaAccount newAccount = new CasaAccount(
            input.getCustomerBetterBankingNumber(),
            input.getAccountNumber(),
            input.getAccountName(),
            input.getProductName(),
            input.getProductTypeCode(),
            input.getAccountNickName(),
            input.getAccountTypeCode(),
            input.getAccountTypeDescription(),
            input.getCurrentBalance(),
            input.getAvailableBalance(),
            input.getCurrency(),
            input.getOpeningDate(),
            input.getBranchName(),
            input.getOwnershipType(),
            input.getSwiftCode(),
            input.getEligibilityFlag(),
            input.getAccountStatus(),
            null, // Assuming specialConditionDetails and productDisplay are not provided
            null, // Assuming specialConditionDetails and productDisplay are not provided
            input.getIsHidden(),
            input.getBlacklistFlag()
        );
        return casaAccountService.createCasaAccount(newAccount);
    }
}