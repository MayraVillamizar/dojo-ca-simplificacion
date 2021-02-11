package co.com.tallergrupo7.restapi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transaction {
    private String startDate;
    private String endDate;
    private float minAmount;
    private float maxAmount;
    private String type;
    private String checkNumber;
    private String group;
    private String description;
}
