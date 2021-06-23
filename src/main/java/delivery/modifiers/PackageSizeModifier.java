package delivery.modifiers;

import delivery.Package;

public class PackageSizeModifier implements PriceModifier {
    @Override public void modify(Package pkg) {
        switch (pkg.getSize()) {
            case BIG:
                pkg.incrementPrice(200);
                break;

            case SMALL:
                pkg.incrementPrice(100);
                break;

            default:
                throw new IllegalArgumentException(String.format("Unknown package size {}. Please modify PackageSize.", pkg.getSize()));
        }
    }

}
