package co.com.tallergrupo7.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

	@PostMapping("/SaldosMovimientos")
	public Mono<BalancesResponse> getBalancesMovements(@RequestBody BalancesRequest request) {

		return useCase.getBalancesMovements(request).map(r -> {
			List<BalancesMovement> data = new ArrayList<>();
			data.add(r);

			return BalancesResponse.builder().data(data).build();
		});
	}

}