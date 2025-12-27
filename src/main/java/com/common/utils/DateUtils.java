package com.common.utils;

import com.common.model.CalendarType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);


    public static Long getMillisFromTimestamp(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(Constants.YEAR_MONTH_DATE_FORMAT, Locale.US);
        LocalDate newDate = LocalDate.parse(date, inputFormatter);
        return newDate.toEpochDay() * Constants.MILLIS_IN_A_DAY;
    }

    public static String getTodayDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        return formatter.format(date);
    }

    public static int getDayFromTimeStamp(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }


    public static long getCurrentTimeInUTC() {
        return OffsetDateTime.now(ZoneOffset.UTC).toInstant().toEpochMilli();
    }

    public static LocalDate getLocalDateFromMillis(Long millis) {
        return Instant.ofEpochMilli(millis).atZone(ZoneOffset.UTC).toLocalDate();
    }

    public static String getJourneyStartTimeInUtc(String journeyStartTime, String timezone) {
        String startTime = journeyStartTime + timezone;
        OffsetTime offsetTime = OffsetTime.parse(startTime);
        OffsetTime utcTime = offsetTime.withOffsetSameInstant(ZoneOffset.UTC);
        return utcTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    /**
     * @param millis
     * @return
     */
    public int getMonthFromArrivalDate(Long millis) {
        LocalDate time = Instant.ofEpochMilli(millis).atZone(ZoneOffset.UTC).toLocalDate();
        return time.getMonthValue();
    }

    public static long getTodaysDate() {
        return LocalDate.now().atStartOfDay().toEpochSecond(ZoneOffset.UTC) * 1000;
    }


    public static int minutesBetweenTwoDates(Long date1, Long date2) {
        long delta = date2 - date1;

        return (int) TimeUnit.MILLISECONDS.toMinutes(delta);
    }

    public static long getYesterdayEndTimeMillis(String timeZone) {
        ZoneId zoneId = ZoneId.of(timeZone);
        return LocalDateTime.now().minusDays(1).with(LocalTime.MAX).atZone(zoneId).toEpochSecond() * 1000;
    }

    public static long getParticularTimeOfTheDay(int time, int minutes) {

        LocalDateTime todayAtParticularHour = LocalDateTime.now().withHour(time).withMinute(minutes).withSecond(0);
        ZonedDateTime zdt = todayAtParticularHour.atZone(ZoneId.of("Asia/Kolkata"));
        long response = zdt.toInstant().toEpochMilli();

        return response;
    }

    public static String getFinancialYearFromMillisecond(long millis, String timezone) {
        Calendar calendar = Calendar.getInstance();
        TimeZone tz = TimeZone.getTimeZone(timezone);
        calendar.setTimeZone(tz);
        calendar.setTimeInMillis(millis);
        if (calendar.get(Calendar.MONTH) < 3) {
            return String.format("%02d", ((calendar.get(Calendar.YEAR) - 1) % 100)) + "-" + String.format("%02d", (calendar.get(Calendar.YEAR) % 100));
        }
        return String.format("%02d", (calendar.get(Calendar.YEAR) % 100)) + "-" + String.format("%02d", ((calendar.get(Calendar.YEAR) + 1) % 100));
    }

    public static int numberOfNightsBetweenTwoDates(Long date1, Long date2) {

        if ((date1 / 86400000L) == (date2 / 86400000L)) {  // Day-show(both date are from same day) = 1 night
            return 1;
        }
        OffsetDateTime checkInDateTime = OffsetDateTime.ofInstant(Instant.ofEpochMilli(date1), ZoneOffset.UTC);
        OffsetDateTime checkOutDateTime = OffsetDateTime.ofInstant(Instant.ofEpochMilli(date2), ZoneOffset.UTC);

        ZonedDateTime checkInZonedDateTime = checkInDateTime.toZonedDateTime().withZoneSameInstant(ZoneOffset.UTC);
        // Convert check-in time to ZonedDateTime and adjust the time zone offset
        OffsetDateTime adjustedCheckInDateTime = OffsetDateTime.from(checkInZonedDateTime).withHour(12).withMinute(0);

        ZonedDateTime checkOutZonedDateTime = checkOutDateTime.toZonedDateTime().withZoneSameInstant(ZoneOffset.UTC);
        OffsetDateTime adjustedCheckOutDateTime = OffsetDateTime.from(checkOutZonedDateTime).withHour(12).withMinute(0);

        // Calculate the number of nights between the adjusted check-in and check-out
        long numberOfNights = ChronoUnit.DAYS.between(adjustedCheckInDateTime.toLocalDate(), adjustedCheckOutDateTime.toLocalDate());

        return (int) numberOfNights;
    }

    public static long getTodayStartDate(String timezone) throws ParseException {
        DateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
        Date today = new Date();
        formatter.setTimeZone(TimeZone.getTimeZone(timezone));
        return formatter.parse(formatter.format(today)).getTime();
    }


    public static long getTodayEndDate(String timezone) throws ParseException {
        LOGGER.info("==================getTodayStartDate==============");
        DateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
        Date today = new Date();
        formatter.setTimeZone(TimeZone.getTimeZone(timezone));
        return formatter.parse(formatter.format(today)).getTime() + Constants.MILLIS_IN_A_DAY;
    }

    public static String getDateFromMillisUsingTimeZone(Long millis, String timezone) {
        Instant instant = Instant.ofEpochMilli(millis);
        LocalDate date = instant.atZone(ZoneId.of(timezone)).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }

    public static long getEndOfDayMillis(long epochMillis) {
        LocalDate date = Instant.ofEpochMilli(epochMillis)
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return date.atTime(23, 59, 59, 999)
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
    }

    public static LocalDate getLocalDateFromMillis(Long millis, String timezone) {
        Instant instant = Instant.ofEpochMilli(millis);
        return instant.atZone(ZoneId.of(timezone)).toLocalDate();
    }

    public static long getTodayLastTimeByZone(String zone) {

        ZoneId zoneId = ZoneId.of(zone);
        ZonedDateTime currentZonedDateTime = ZonedDateTime.now(zoneId);
        LocalTime lastTime = LocalTime.of(23, 59, 59, 999999999);

        ZonedDateTime lastZonedDateTime = currentZonedDateTime
                .toLocalDate()
                .atTime(lastTime)
                .atZone(zoneId);

        return lastZonedDateTime
                .toInstant()
                .toEpochMilli();
    }

    public static long getStartOfDayFromTimeStamp(long timestamp, String timezone) {
        LocalDate localDate = Instant.ofEpochMilli(timestamp).atZone(ZoneId.of(timezone)).toLocalDate();
        return localDate.atStartOfDay().toInstant(ZoneOffset.of(timezone)).toEpochMilli();
    }

    public static long getEndOfDayFromTimeStamp(long timestamp, String timezone) {
        LocalDate localDate = Instant.ofEpochMilli(timestamp).atZone(ZoneId.of(timezone)).toLocalDate();
        return localDate.atTime(23, 59, 59, 999)
                .atZone(ZoneId.of(timezone))
                .toInstant()
                .toEpochMilli();
    }

    /**
     * This method add days,weeks,months and years to a provided date.
     * @param date
     * @param unit
     * @param calendarType
     * @param timeZone
     * @return
     */

    public static long plusCalendarTypeInDate(long date, int unit, CalendarType calendarType, String timeZone){

        LocalDateTime dateTime = Instant.ofEpochMilli(date)
                .atZone(ZoneId.of(timeZone))
                .toLocalDateTime();
        dateTime = switch (calendarType) {
            case WEEK -> dateTime.plusWeeks(unit);
            case MONTH -> dateTime.plusMonths(unit);
            case YEAR -> dateTime.plusYears(unit);
            default -> dateTime.plusDays(unit);
        };
        return dateTime
                .atZone(ZoneId.of(timeZone))
                .toInstant()
                .toEpochMilli();
    }

}
