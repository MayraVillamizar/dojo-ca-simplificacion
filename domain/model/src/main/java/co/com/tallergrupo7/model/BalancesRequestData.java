package co.com.tallergrupo7.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BalancesRequestData {

	@JsonProperty(required = true)
	private Account account;
	private TransactionFilter filter;

}
