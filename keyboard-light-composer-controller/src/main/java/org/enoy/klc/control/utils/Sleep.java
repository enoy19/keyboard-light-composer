package org.enoy.klc.control.utils;

public class Sleep {

    public static void sleep(long millis) {

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // nothing
        }

    }

}
