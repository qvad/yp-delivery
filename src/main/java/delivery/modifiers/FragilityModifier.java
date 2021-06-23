package delivery.modifiers;

import delivery.Package;
import delivery.helpers.ImpossibleDeliveryException;
import delivery.parameter.Distance;

public class FragilityModifier implements PriceModifier {
    @Override public void modify(Package pkg) throws ImpossibleDeliveryException {
        switch (pkg.getFragility()) {
            case FRAGILE:
                if (pkg.getDistance() == Distance.MORE_30KM) {
                    throw new ImpossibleDeliveryException("Deliver of fragile packages is not possible for distances more than 30 km");
                }

                pkg.incrementPrice(300);
                break;

            case ACE_VENTURA:
                break;

            default:
                throw new IllegalArgumentException(String.format("Unknown fragility {}. Please modify PackageFragility.", pkg.getFragility()));
        }
    }

}
