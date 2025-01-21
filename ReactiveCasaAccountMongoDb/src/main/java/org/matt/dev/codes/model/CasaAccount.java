package org.matt.dev.codes.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "Casa_Accounts")
public class CasaAccount {
    @Id
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
    private Integer eligibilityFlag;
    private String accountStatus;
    private List<SpecialConditionDetail> specialConditionDetails;
    private ProductDisplay productDisplay;
    private boolean isHidden;
    private Integer blacklistFlag;


}