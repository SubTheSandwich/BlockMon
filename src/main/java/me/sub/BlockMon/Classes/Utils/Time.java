package me.sub.BlockMon.Classes.Utils;

public class Time {

    public static String format(long durationInNanos) {

        if (durationInNanos == 0) {
            return "0 nanoseconds";
        }

        long millis = durationInNanos / 1000000;
        long nanos = durationInNanos % 1000000;

        // Breakdown into days, hours, minutes, seconds, milliseconds, and nanoseconds
        long days = millis / (24 * 60 * 60 * 1000);
        millis %= (24 * 60 * 60 * 1000);

        long hours = millis / (60 * 60 * 1000);
        millis %= (60 * 60 * 1000);

        long minutes = millis / (60 * 1000);
        millis %= (60 * 1000);

        long seconds = millis / 1000;
        millis %= 1000;

        StringBuilder result = new StringBuilder();

        if (days > 0) {
            result.append(days).append(" day").append(days > 1 ? "s " : " ");
        }
        if (hours > 0) {
            result.append(hours).append(" hour").append(hours > 1 ? "s " : " ");
        }
        if (minutes > 0) {
            result.append(minutes).append(" minute").append(minutes > 1 ? "s " : " ");
        }
        if (seconds > 0) {
            result.append(seconds).append(" second").append(seconds > 1 ? "s " : " ");
        }
        if (millis > 0) {
            result.append(millis).append(" millisecond").append(millis > 1 ? "s " : " ");
        }
        if (nanos > 0) {
            result.append(nanos).append(" nanosecond").append(nanos > 1 ? "s" : "");
        }

        return result.toString().trim();
    }
}
