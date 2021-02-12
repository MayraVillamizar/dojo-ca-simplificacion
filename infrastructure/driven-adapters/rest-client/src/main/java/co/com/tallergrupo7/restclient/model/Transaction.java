package co.com.tallergrupo7.restclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
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
