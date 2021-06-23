package delivery.modifiers;

import delivery.Package;

public class DistanceModifier implements PriceModifier {
    @Override public void modify(Package pkg) {
        switch (pkg.getDistance()) {
            case MORE_30KM:
                pkg.incrementPrice(300);
                break;

            case BETWEEN_10_30KM:
                pkg.incrementPrice(200);
                break;

            case BETWEEN_2_10KM:
                pkg.incrementPrice(100);
                break;

            case LESS_2KM:
                pkg.incrementPrice(50);
                break;

            default:
                throw new IllegalArgumentException(String.format("Unknown distance {}. Please modify Distance.", pkg.getDistance()));
        }
    }
}
