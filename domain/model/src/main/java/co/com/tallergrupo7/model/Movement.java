package co.com.tallergrupo7.model;

import lombok.Data;

@Data
public class Movement {

	private String id;
	private String postedDate;
	private String description;
	private String amount;
	private String type;
	private String reference1;
	private String reference2;
	private String reference3;
	private String checkNumber;
}
