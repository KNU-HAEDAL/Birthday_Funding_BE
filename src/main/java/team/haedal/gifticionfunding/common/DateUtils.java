package team.haedal.gifticionfunding.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public final class DateUtils{
    public static String dateToString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


    public static long diff(LocalDate start, LocalDate end){
        return ChronoUnit.DAYS.between(
                LocalDate.of(1, start.getMonth(), start.getDayOfMonth()),
                LocalDate.of(1, end.getMonth(), end.getDayOfMonth()));
    }

}
