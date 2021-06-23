package delivery;

import delivery.parameter.DeliveryWorkload;
import delivery.parameter.PackageFragility;
import delivery.parameter.PackageSize;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class DeliveryManagerTest {

    private float distance;
    private PackageFragility fragility;
    private PackageSize size;
    private DeliveryWorkload workload;
    private int expectedResult;

    private DeliveryManager manager;

    @Before
    public void beforeMethod() {
        manager = new DeliveryManager();
    }

    public DeliveryManagerTest(float distance, PackageFragility fragility, PackageSize size,
        DeliveryWorkload workload, int expectedResult) {
        super();
        this.distance = distance;
        this.fragility = fragility;
        this.size = size;
        this.workload = workload;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection positiveParameters() {
        return Arrays.asList(new Object[][] {
            // default cases with minimal price
            {20f, PackageFragility.ACE_VENTURA, PackageSize.BIG, DeliveryWorkload.DEFAULT, 400},
            {20, PackageFragility.ACE_VENTURA, PackageSize.BIG, DeliveryWorkload.DEFAULT, 400},
            {0.1f, PackageFragility.ACE_VENTURA, PackageSize.SMALL, DeliveryWorkload.DEFAULT, 400},

            // max delivery distance (very profitable)
            {40000f, PackageFragility.ACE_VENTURA, PackageSize.SMALL, DeliveryWorkload.DEFAULT, 400},

            // edge cases 30
            {31.0f, PackageFragility.ACE_VENTURA, PackageSize.SMALL, DeliveryWorkload.DEFAULT, 400},
            {30.0f, PackageFragility.FRAGILE, PackageSize.SMALL, DeliveryWorkload.DEFAULT, 600},
            {29.9f, PackageFragility.FRAGILE, PackageSize.SMALL, DeliveryWorkload.DEFAULT, 600},

            // edge cases 10
            {10.1f, PackageFragility.FRAGILE, PackageSize.SMALL, DeliveryWorkload.DEFAULT, 600},
            {10.0f, PackageFragility.FRAGILE, PackageSize.SMALL, DeliveryWorkload.DEFAULT, 500},
            {9.9f, PackageFragility.FRAGILE, PackageSize.SMALL, DeliveryWorkload.DEFAULT, 500},

            // edge cases 2
            {2.1f, PackageFragility.FRAGILE, PackageSize.SMALL, DeliveryWorkload.DEFAULT, 500},
            {2.0f, PackageFragility.FRAGILE, PackageSize.SMALL, DeliveryWorkload.DEFAULT, 450},
            {1.9f, PackageFragility.FRAGILE, PackageSize.SMALL, DeliveryWorkload.DEFAULT, 450},

            // edge cases 0
            {0.1f, PackageFragility.FRAGILE, PackageSize.SMALL, DeliveryWorkload.DEFAULT, 450},

            // positive parameter as string
            {30.0f, PackageFragility.FRAGILE, PackageSize.valueOf("SMALL"), DeliveryWorkload.DEFAULT, 600},
            {30.0f, PackageFragility.FRAGILE, PackageSize.SMALL, DeliveryWorkload.valueOf("DEFAULT"), 600},
            {30.0f, PackageFragility.valueOf("FRAGILE"), PackageSize.SMALL, DeliveryWorkload.DEFAULT, 600},

            // pairwise generated common cases
            {40f, PackageFragility.ACE_VENTURA, PackageSize.SMALL, DeliveryWorkload.HIGH, 560},
            {40f, PackageFragility.ACE_VENTURA, PackageSize.SMALL, DeliveryWorkload.DEFAULT, 400},

            {20f, PackageFragility.ACE_VENTURA, PackageSize.BIG, DeliveryWorkload.DEFAULT, 400},
            {20f, PackageFragility.ACE_VENTURA, PackageSize.BIG, DeliveryWorkload.VERY_HIGH, 640},
            {20f, PackageFragility.FRAGILE, PackageSize.BIG, DeliveryWorkload.HIGH, 980},
            {20f, PackageFragility.FRAGILE, PackageSize.SMALL, DeliveryWorkload.INCREASED, 720},

            {8f, PackageFragility.FRAGILE, PackageSize.BIG, DeliveryWorkload.HIGH, 840},
            {8f, PackageFragility.ACE_VENTURA, PackageSize.BIG, DeliveryWorkload.INCREASED, 400},
            {8f, PackageFragility.FRAGILE, PackageSize.SMALL, DeliveryWorkload.DEFAULT, 500},
            {8f, PackageFragility.ACE_VENTURA, PackageSize.SMALL, DeliveryWorkload.VERY_HIGH, 400},

            {1.2f, PackageFragility.FRAGILE, PackageSize.BIG, DeliveryWorkload.VERY_HIGH, 880},
            {1.2f, PackageFragility.FRAGILE, PackageSize.SMALL, DeliveryWorkload.HIGH, 630},
            {1.2f, PackageFragility.ACE_VENTURA, PackageSize.BIG, DeliveryWorkload.INCREASED, 400},
            {1.2f, PackageFragility.FRAGILE, PackageSize.BIG, DeliveryWorkload.DEFAULT, 550}
        });
    }

    @Test
    public void testPositiveParametrized() throws Exception {
        System.out.println("Parameters: " + fragility + ", " + distance + ", " + size + ", " + workload);
        assertEquals(expectedResult, manager.getDeliveryPrice(distance, size, fragility, workload));
    }
}
