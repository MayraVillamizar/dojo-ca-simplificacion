package co.com.tallergrupo7.api;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.com.tallergrupo7.model.BalancesMovement;
import co.com.tallergrupo7.model.BalancesRequest;
import co.com.tallergrupo7.model.BalancesResponse;
import co.com.tallergrupo7.usecase.balancemovement.BalanceMovementUseCase;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tallergrupo7")
@AllArgsConstructor
public class ApiRest {

	private final BalanceMovementUseCase useCase;
	private final Logger logger = LoggerFactory.getLogger(ApiRest.class);

	@PostMapping("/SaldosMovimientos")
	public Mono<BalancesResponse> getBalancesMovements(@RequestBody BalancesRequest request) {

		return useCase.getBalancesMovements(request).map(r -> {
			logger.info("response: "+ r);
			List<BalancesMovement> data = new ArrayList<>();
			data.add(r);

			return BalancesResponse.builder().data(data).build();
		}).onErrorResume(throwable -> this.validateError(throwable));

	}

		private Mono<BalancesResponse> validateError(Throwable e){
			logger.error(e.getMessage(),e);
			return Mono.just(BalancesResponse.builder().build());
		}
}