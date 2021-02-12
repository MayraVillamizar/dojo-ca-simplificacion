package co.com.tallergrupo7.usecase.balancemovement;

import java.util.List;

import co.com.tallergrupo7.model.Balances;
import co.com.tallergrupo7.model.BalancesMovement;
import co.com.tallergrupo7.model.BalancesRequest;
import co.com.tallergrupo7.model.Movement;
import co.com.tallergrupo7.model.exceptions.BalancesBusinessException;
import co.com.tallergrupo7.model.gateways.BalancesGateway;
import co.com.tallergrupo7.model.gateways.MovementsGateway;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BalanceMovementUseCase {

	private static final String MSG_NO_DATA_FOUND = "No se encontraron movimientos para la cuenta dada";

	private final BalancesGateway balancesGateway;
	private final MovementsGateway movementsGateway;


	public Mono<BalancesMovement> getBalancesMovements(BalancesRequest balancesRequest) {

		Mono<Balances> balances = balancesGateway.consultBalances(balancesRequest.getData().get(0).getAccount());

		Mono<List<Movement>> movements = movementsGateway.consultMovements(balancesRequest.getData().get(0));

		return Mono.zip(balances, movements).flatMap(r -> {
			BalancesMovement balancesMovement = BalancesMovement.builder().balances(r.getT1()).movements(r.getT2())
					.build();
			return Mono.just(balancesMovement);
		}).switchIfEmpty(Mono.error(new BalancesBusinessException(MSG_NO_DATA_FOUND)));

	}
}
