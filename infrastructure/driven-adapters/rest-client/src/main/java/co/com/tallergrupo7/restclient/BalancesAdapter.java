package co.com.tallergrupo7.restclient;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import co.com.tallergrupo7.model.Account;
import co.com.tallergrupo7.model.Balances;
import co.com.tallergrupo7.model.gateways.BalancesGateway;
import co.com.tallergrupo7.restclient.model.RetrieveBalanceRequest;
import co.com.tallergrupo7.restclient.model.RetrieveBalanceRequestData;
import co.com.tallergrupo7.restclient.model.RetrieveBalanceResponse;
import reactor.core.publisher.Mono;

@Component
public class BalancesAdapter implements BalancesGateway {

	@Value("${url.balances}")
	private String url;

	@Override
	public Mono<Balances> consultBalances(Account account) {

		List<RetrieveBalanceRequestData> data = new ArrayList<>();
		data.add(RetrieveBalanceRequestData.builder().account(account).build());
		RetrieveBalanceRequest request = RetrieveBalanceRequest.builder().data(data).build();

		return WebClient.create(url).post().contentType(MediaType.APPLICATION_JSON)
				.body(Mono.just(request), RetrieveBalanceRequest.class).retrieve()
				.bodyToMono(RetrieveBalanceResponse.class)
				.map(r -> r.getData().get(0).getAccount().getBalances());

	}


}