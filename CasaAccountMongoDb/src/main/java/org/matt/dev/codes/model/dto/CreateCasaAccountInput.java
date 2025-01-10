package org.matt.dev.codes.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateCasaAccountInput {
    private String customerBetterBankingNumber;
    private String accountNumber;
    private String accountName;
    private String productName;
    private String productTypeCode;
    private String accountNickName;
    private String accountTypeCode;
    private String accountTypeDescription;
    private BigDecimal currentBalance;
    private BigDecimal availableBalance;
    private String currency;
    private String openingDate;
    private String branchName;
    private String ownershipType;
    private String swiftCode;
    private int eligibilityFlag;
    private String accountStatus;
    private Boolean isHidden;
    private int blacklistFlag;


}