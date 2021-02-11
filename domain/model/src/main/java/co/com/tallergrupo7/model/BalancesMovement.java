package co.com.tallergrupo7.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BalancesMovement {

	private Balances balances;
	private List<Movement> movements;
}
