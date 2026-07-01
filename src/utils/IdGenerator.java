
package utils;

import java.util.Random;

public class IdGenerator {

    static Random random = new Random();

    public static int generateDriverId() {

        return 1000000 + random.nextInt(9000000);
    }

    public static int generatePassengerId() {

        return 1000000 + random.nextInt(9000000);
    }

    public static int generateRideId() {

        return 1000 + random.nextInt(9000);
    }

    public static int generateBookingId() {

        return 10000 + random.nextInt(90000);
    }

    public static int generateRatingId() {

        return 10000 + random.nextInt(90000);
    }

    public static int generateLogId() {

        return 10000 + random.nextInt(90000);
    }

}