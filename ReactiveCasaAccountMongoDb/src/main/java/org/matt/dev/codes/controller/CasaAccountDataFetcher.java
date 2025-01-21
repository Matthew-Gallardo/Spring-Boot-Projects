package org.matt.dev.codes.controller;

import org.matt.dev.codes.model.CasaAccount;
import org.matt.dev.codes.model.dto.CasaAccountInput;
import org.matt.dev.codes.model.dto.CreateCasaAccountInput;
import org.matt.dev.codes.model.dto.UpdateCasaAccountInput;
import org.matt.dev.codes.service.CasaAccountService;
import org.matt.dev.codes.validators.CasaAccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class CasaAccountDataFetcher {

    @Autowired
    private CasaAccountService casaAccountService;

    @Autowired
    private CasaAccountValidator casaAccountValidator;

    @QueryMapping
    public Flux<CasaAccount> getEligibleAndNonBlacklistedAccounts(@Argument String bbn,
                                                                  @Argument String accountType) {
        return casaAccountService.findEligibleAndNonBlacklistedAccounts(bbn, accountType);
    }

    @QueryMapping
    public Mono<CasaAccount> getAccountByBbn(@Argument String bbn){
        return  casaAccountService.findAccountByBbn(bbn);
    }

    @QueryMapping
    public Flux<CasaAccount> getAllAccounts() {
        return casaAccountService.findAllAccounts();
    }

    @MutationMapping
    public Mono<Void> deleteCasaAccountByBbnAndAccountNumber(@Argument CasaAccountInput input) {
        return casaAccountService.deleteCasaAccountByBbnAndAccountNumber(input.getBbn(), input.getAccountNumber());
    }

    @MutationMapping
    public Mono<CasaAccount> updateCasaAccount(@Argument UpdateCasaAccountInput input) {
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
        casaAccountValidator.validate(updatedAccount);
        return casaAccountService.updateCasaAccount(updatedAccount);
    }

    @MutationMapping
    public Mono<CasaAccount> createCasaAccount(@Argument CreateCasaAccountInput input) {
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
        casaAccountValidator.validate(newAccount);
        return casaAccountService.createCasaAccount(newAccount);
    }
}