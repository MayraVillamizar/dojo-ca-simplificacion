package co.com.tallergrupo7.restclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RetrieveTransactionRequest {

    private List<RetrieveTransactionRequestData> data;
}
