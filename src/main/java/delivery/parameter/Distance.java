package delivery.parameter;

public enum Distance {
    MORE_30KM,
    BETWEEN_10_30KM,
    BETWEEN_2_10KM,
    LESS_2KM;

    public static final Distance create(float distance) {
        if (distance > 40000f) {
            throw new IllegalArgumentException("Are you trying to deliver something not on Earth?");
        }

        if (distance > 30.0f) {
            return MORE_30KM;
        } else if (distance > 10.0f) {
            return BETWEEN_10_30KM;
        } else if (distance > 2.0f) {
            return BETWEEN_2_10KM;
        } else if (distance > 0.0f) {
            return LESS_2KM;
        } else {
            throw new IllegalArgumentException("Distance must be more that zero");
        }
    }
}
