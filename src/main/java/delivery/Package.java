package delivery;

import delivery.helpers.ImpossibleDeliveryException;
import delivery.modifiers.DeliveryWorkloadModifier;
import delivery.modifiers.DistanceModifier;
import delivery.modifiers.FragilityModifier;
import delivery.modifiers.PackageSizeModifier;
import delivery.modifiers.PriceModifier;
import delivery.parameter.DeliveryWorkload;
import delivery.parameter.Distance;
import delivery.parameter.PackageFragility;
import delivery.parameter.PackageSize;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Package {

    private final static int MINIMAL_PRICE_THRESHOLD = 400;

    private int price;

    private final Distance distance;
    private final PackageFragility fragility;
    private final PackageSize packageSize;
    private final DeliveryWorkload deliveryWorkload;

    private final List<PriceModifier> incModifiers = Arrays.asList(
        new DistanceModifier(),
        new FragilityModifier(),
        new PackageSizeModifier()
    );

    private final List<PriceModifier> mulModifiers = Collections.singletonList(
        new DeliveryWorkloadModifier()
    );

    public Package(Distance distance, PackageFragility fragility, PackageSize size,
        DeliveryWorkload workload) {
        this.distance = distance;
        this.fragility = fragility;
        packageSize = size;
        deliveryWorkload = workload;
    }

    public Distance getDistance() {
        return distance;
    }

    public PackageFragility getFragility() {
        return fragility;
    }

    public PackageSize getSize() {
        return packageSize;
    }

    public DeliveryWorkload getDeliveryWorkload() {
        return deliveryWorkload;
    }

    public void incrementPrice(int inc) {
        this.price += inc;
    }

    public void multiplyPrice(float coefficient) {
        this.price = Math.round(price * coefficient);
    }

    public int getPrice() throws ImpossibleDeliveryException  {
        // first get additive modifiers
        for (PriceModifier priceModifier : incModifiers) {
            priceModifier.modify(this);
        }

        // then multiply modifiers
        for (PriceModifier priceModifier : mulModifiers) {
            priceModifier.modify(this);
        }

        if (price < MINIMAL_PRICE_THRESHOLD) {
            this.price = MINIMAL_PRICE_THRESHOLD;
        }

        return this.price;
    }
}
