package delivery;

import delivery.helpers.ImpossibleDeliveryException;
import delivery.parameter.DeliveryWorkload;
import delivery.parameter.PackageFragility;
import delivery.parameter.PackageSize;
import org.junit.Test;

public class DeliveryManagerNegativeTest {

    @Test(expected = IllegalArgumentException.class)
    public void testZeroDistanceDelivery() throws Exception {
        DeliveryManager manager = new DeliveryManager();
        manager.getDeliveryPrice(0.0f, PackageSize.SMALL, PackageFragility.FRAGILE, DeliveryWorkload.DEFAULT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLessZeroDistanceDelivery() throws Exception {
        DeliveryManager manager = new DeliveryManager();
        manager.getDeliveryPrice(-1f, PackageSize.SMALL, PackageFragility.FRAGILE, DeliveryWorkload.DEFAULT);
    }

    @Test(expected = ImpossibleDeliveryException.class)
    public void testMaxFragileDelivery() throws Exception {
        DeliveryManager manager = new DeliveryManager();
        manager.getDeliveryPrice(30.1f, PackageSize.SMALL, PackageFragility.FRAGILE, DeliveryWorkload.DEFAULT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongArgumentsDelivery() throws Exception {
        DeliveryManager manager = new DeliveryManager();
        manager.getDeliveryPrice(40000.1f, PackageSize.valueOf("not small"), PackageFragility.FRAGILE, DeliveryWorkload.DEFAULT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInterplanetaryDelivery() throws Exception {
        DeliveryManager manager = new DeliveryManager();
        manager.getDeliveryPrice(40000.1f, PackageSize.SMALL, PackageFragility.FRAGILE, DeliveryWorkload.DEFAULT);
    }

    // no need to test wrong PackageSize, PackageFragility and DeliveryWorkload because of it's enum

    // we are testing Java here, so not much tests, just for PackageSize
    @Test(expected = IllegalArgumentException.class)
    public void testTextArgumentsDelivery() throws Exception {
        DeliveryManager manager = new DeliveryManager();
        manager.getDeliveryPrice(40000.1f, PackageSize.valueOf("small"), PackageFragility.FRAGILE, DeliveryWorkload.DEFAULT);
    }
}
