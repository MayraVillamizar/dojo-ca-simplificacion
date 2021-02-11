package co.com.tallergrupo7.restapi.model;

import co.com.tallergrupo7.model.Account;
import co.com.tallergrupo7.model.Movement;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class RetrieveTransactionResponseData {

    private List<Movement> transaction;
    private Office office;
    private Account relatedTransferAccount;
    private int _responseSize;
    private boolean _flagMoreRecords;

}
