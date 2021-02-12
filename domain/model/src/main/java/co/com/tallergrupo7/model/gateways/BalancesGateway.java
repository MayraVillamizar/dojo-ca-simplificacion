package co.com.tallergrupo7.model.gateways;

import co.com.tallergrupo7.model.Account;
import co.com.tallergrupo7.model.Balances;
import lombok.Builder;
import reactor.core.publisher.Mono;


public interface BalancesGateway {

	public Mono<Balances> consultBalances(Account account);

}
