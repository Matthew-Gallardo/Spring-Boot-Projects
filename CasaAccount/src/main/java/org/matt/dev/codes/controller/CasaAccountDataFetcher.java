package org.matt.dev.codes.controller;

import java.util.List;

import org.matt.dev.codes.model.CasaAccount;
import org.matt.dev.codes.model.SpecialConditionDetail;
import org.matt.dev.codes.model.dto.CasaAccountInput;
import org.matt.dev.codes.model.dto.UpdateCasaAccountInput;
import org.matt.dev.codes.service.CasaAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

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
    public CasaAccount getAccountByBbn(@Argument String bbn) {
    	return casaAccountService.findAccountByBbnAccount(bbn);
    }

    @QueryMapping
    public List<CasaAccount> getAllAccounts() {
        return casaAccountService.findAllAccounts();
    }
    
    @QueryMapping
    public List<SpecialConditionDetail> getAllSpecialConditionDetails(){
    	return casaAccountService.findAllSpecialConditionDetails();
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
}