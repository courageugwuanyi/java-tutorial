package control.flow;

//import org.joda.time.format.DateTimeFormatter;
//import org.joda.time.format.ISODateTimeFormat;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.time.format.DateTimeFormatter;

public class TestPlayground {
    /**
     * This method calculates the sum of two integers.
     * <p>
     * Usage example:
     * <pre>{@code
     * int sum = add(5, 3);
     * System.out.println(sum); // Outputs: 8
     * }</pre>
     *</p>
     * @param a the first integer
     * @param b the second integer
     * @return the sum of {@code a} and {@code b}
     */
    public int add(int a, int b) {
        return a + b;
    }
    public static void main(String[] args) {
        Date now = new Date();
        org.joda.time.format.DateTimeFormatter dtf = org.joda.time.format.ISODateTimeFormat.dateTimeNoMillis();
        String time = dtf.print(now.getTime());
        System.out.println(time);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        OffsetDateTime dateTimeNow = OffsetDateTime.now();

        String formattedDateTime = dateTimeNow.format(dateTimeFormatter);
        System.out.println(formattedDateTime.replaceFirst(".\\d{6}", ""));
    }
}
