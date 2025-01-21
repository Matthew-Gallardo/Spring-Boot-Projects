package org.matt.dev.codes.validators;

import org.matt.dev.codes.model.CasaAccount;
import org.matt.dev.codes.exception.NoMessageException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Component
public class CasaAccountValidator {

    public void validate(CasaAccount account) {
        Map<String, Supplier<Object>> fields = new HashMap<>();
        fields.put("Account number", account::getAccountNumber);
        fields.put("Account name", account::getAccountName);
        fields.put("Account nickname", account::getAccountNickName);
        fields.put("Account status", account::getAccountStatus);
        fields.put("Account Type Description", account::getAccountTypeDescription);
        fields.put("Account type code", account::getAccountTypeCode);
        fields.put("Currency", account::getCurrency);
        fields.put("Opening Date", account::getOpeningDate);
        fields.put("Branch Name", account::getBranchName);
        fields.put("Ownership type", account::getOwnershipType);
        fields.put("Swift code", account::getSwiftCode);
        fields.put("Product name", account::getProductName);
        fields.put("Product Type Code", account::getProductTypeCode);
        fields.put("Blacklist flag", account::getBlacklistFlag);
        fields.put("Current Balance", account::getCurrentBalance);
        fields.put("Available balance", account::getAvailableBalance);

        fields.forEach((fieldName, supplier) -> {
            Object value = supplier.get();
            if (value == null || (value instanceof String && ((String) value).trim().isEmpty())) {
                throw new NoMessageException(fieldName + " cannot be blank");
            }
        });
    }
}