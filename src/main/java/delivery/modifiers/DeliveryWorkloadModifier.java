package delivery.modifiers;

import delivery.Package;

public class DeliveryWorkloadModifier implements PriceModifier {
    @Override public void modify(Package pkg) {
        switch (pkg.getDeliveryWorkload()) {
            case VERY_HIGH:
                pkg.multiplyPrice(1.6f);
                break;

            case HIGH:
                pkg.multiplyPrice(1.4f);
                break;

            case INCREASED:
                pkg.multiplyPrice(1.2f);
                break;

            case DEFAULT:
                break;

            default:
                throw new IllegalArgumentException(String.format("Unknown delivery workload {}. Please modify DeliveryWorkload.", pkg.getDeliveryWorkload()));
        }
    }

}
