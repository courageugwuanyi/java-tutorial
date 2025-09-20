package datastore;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

public class TimeTest {
    public static void main(String[] args) {
        // creating a date for today
        LocalDate now = LocalDate.now();
        System.out.println(now.getDayOfWeek());
        System.out.println(now.getDayOfYear());
        System.out.println(now.getMonthValue());
        System.out.println(now.plusDays(4));
        System.out.println(now.minusDays(4));
        System.out.println(now.plus(3, ChronoUnit.YEARS));

        LocalDate newYears = LocalDate.of(2025, 1, 10);
        System.out.println(newYears.getDayOfWeek());

//        now.datesUntil(newYears, Period.ofMonths(1))
//                .forEach(System.out::println);

        List<LocalDate> leapYears = now.datesUntil(now.plusYears(10), Period.ofYears(1))
                .filter(LocalDate::isLeapYear)
                .collect(Collectors.toList());
        System.out.println(leapYears);

        LocalDate d1 = LocalDate.of(2020, 1, 1);
        LocalDate d2 = LocalDate.of(2024, 6, 20);
        System.out.println(d1.compareTo(d2));

        LocalTime timeNow = LocalTime.now();
        System.out.println(timeNow);

        LocalTime lTime1 = LocalTime.of(13, 50, 1);
        LocalTime lTime2 = LocalTime.of(10, 30);
        System.out.println(lTime1.equals(lTime2));

        LocalDateTime ldt1 = LocalDateTime.of(d1, lTime1);
        LocalDateTime ldt2 = LocalDateTime.of(d2, lTime2);
        System.out.println(ldt2.equals(ldt1));

        // TimeStamp for Machines to compare or Systems to compare with, use the epoch time
        // .... 1970 Jan 1st, is the epoch time
        System.out.println(Instant.now());

        // Duration or Period...AMount of time elapsed between d1 and d2
        Period diff = Period.between(d1, d2);
        Duration duration = Duration.between(lTime2, lTime1);
        System.out.println(diff);
        System.out.printf("%d years, %d months, %d days\n", diff.getYears(), diff.getMonths(), diff.getDays());
        System.out.println(duration.toMinutes());
        System.out.println(duration.toHoursPart());

        // TimeZone. This is based on the computer timezone
        ZonedDateTime timeZoneTIme = ZonedDateTime.of(ldt1, ZoneId.systemDefault());
        ZonedDateTime timeZoneTIme2 = ZonedDateTime.of(ldt1, ZoneId.of("America/New_York"));
        System.out.println(timeZoneTIme);
        System.out.println(timeZoneTIme2);

        // THis is the information sent from the users browsers
        // Additional information: California - GMT-08.
        LocalDateTime xmas = LocalDateTime.of(2021, 12, 25, 20, 00);
        ZonedDateTime zxmas = ZonedDateTime.of(xmas, ZoneId.of("-8"));
        // normalize the time and find the date time in GMT
        System.out.println(zxmas.withZoneSameInstant(ZoneId.of("+0")));

        // The with() allows us to change the dates of the LocalDate and LocalTime Objects
        LocalDate dateOfTheNextSunday = d1.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        LocalDate modifiedDate = d1.withMonth(3).withDayOfMonth(3);
        System.out.println(dateOfTheNextSunday);
        System.out.println(modifiedDate);


//        System.out.println(d1.with(TemporalAdjusters.lastDayOfYear()));
//        System.out.println(d1.with(TemporalAdjusters.lastDayOfMonth()));
//        System.out.println(d1.with(TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY)));



    }
}
