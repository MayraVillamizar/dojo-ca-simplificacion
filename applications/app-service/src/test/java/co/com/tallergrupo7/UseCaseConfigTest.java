package co.com.tallergrupo7;

import co.com.tallergrupo7.config.UseCaseConfig;
import co.com.tallergrupo7.model.gateways.BalancesGateway;
import co.com.tallergrupo7.model.gateways.MovementsGateway;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class UseCaseConfigTest {

    @Mock
    private MovementsGateway movementsGateway;

    @Mock
    private BalancesGateway balancesGateway;

    @Test
    public void validateBalancesMovementsUseCase(){

        UseCaseConfig useCaseConfig = new UseCaseConfig();
        Assert.assertNotNull(useCaseConfig.balancesMovementsUseCase(balancesGateway,movementsGateway));

    }
}
