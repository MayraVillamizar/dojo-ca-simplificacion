package co.com.tallergrupo7.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
public class Balances {
    private BigDecimal available;
    private BigDecimal availableOverdraftBalance;
    private BigDecimal overdraftValue;
    private BigDecimal availableOverdraftQuota;
    private BigDecimal cash;
    private BigDecimal clearing;
    private BigDecimal receivable;
    private BigDecimal blocked;
    private BigDecimal clearingStartDay;
    private BigDecimal cashStartDay;
    private BigDecimal pockets;
    private BigDecimal remittanceQuota;
    private BigDecimal agreedRemittanceQuota;
    private BigDecimal remittanceQuotaUsage;
    private BigDecimal normalInterest;
    private BigDecimal suspensionInterest;
}
