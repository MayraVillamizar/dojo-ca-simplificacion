package co.com.tallergrupo7.restapi.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RetrieveTransactionRequest {

    private List<RetrieveTransactionRequestData> data;
}
