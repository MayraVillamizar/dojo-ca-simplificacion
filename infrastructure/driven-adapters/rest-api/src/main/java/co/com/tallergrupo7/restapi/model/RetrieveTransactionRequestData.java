package co.com.tallergrupo7.restapi.model;

import co.com.tallergrupo7.model.Account;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RetrieveTransactionRequestData {
    private Pagination pagination;
    private Account account;
    private Transaction transaction;
    private Office office;
}
