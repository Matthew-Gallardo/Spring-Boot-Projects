package org.matt.dev.codes.repository;


import org.matt.dev.codes.model.CasaAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface CasaAccountRepository extends MongoRepository<CasaAccount, String> {
    Optional<CasaAccount> findByCustomerBetterBankingNumberAndAccountNumber(String customerBetterBankingNumber, String accountNumber);
}
