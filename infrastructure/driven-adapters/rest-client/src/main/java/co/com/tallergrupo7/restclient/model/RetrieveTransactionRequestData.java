package co.com.tallergrupo7.restclient.model;

import co.com.tallergrupo7.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RetrieveTransactionRequestData {
    private Pagination pagination;
    private Account account;
    private Transaction transaction;
    private Office office;
}
