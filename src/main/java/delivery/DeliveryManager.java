package delivery;

import delivery.helpers.ImpossibleDeliveryException;
import delivery.parameter.DeliveryWorkload;
import delivery.parameter.Distance;
import delivery.parameter.PackageFragility;
import delivery.parameter.PackageSize;

public class DeliveryManager {
    public int getDeliveryPrice(float distance,
        PackageSize size,
        PackageFragility fragility,
        DeliveryWorkload workload) throws ImpossibleDeliveryException {
        Package pkg = new Package(Distance.create(distance), fragility, size, workload);
        return pkg.getPrice();
    }
}
