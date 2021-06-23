package delivery.modifiers;

import delivery.Package;
import delivery.helpers.ImpossibleDeliveryException;

public interface PriceModifier {
    void modify(Package pkg) throws ImpossibleDeliveryException;
}
