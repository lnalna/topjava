package ru.javawebinar.topjava.util;


import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

public class DateTimeFormatters {

         public static class LocalDateFormatter implements Formatter<LocalDate> {

             @Override
             public String print(LocalDate localDate, Locale locale) {
                 return localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
             }

             @Override
             public LocalDate parse(String text, Locale locale) throws ParseException {
                 return parseLocalDate(text);
             }
         }

         public static class LocalTimeFormatter implements Formatter<LocalTime> {

             @Override
             public String print(LocalTime localTime, Locale locale) {
                 return localTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
             }

             @Override
             public LocalTime parse(String text, Locale locale) throws ParseException {
                 return parseLocalTime(text);
             }
         }
}
