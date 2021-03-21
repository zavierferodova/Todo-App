package com.zavierdev.todoapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
    public static String stringDateIndonesiaFormat(String stringDate) {
        SimpleDateFormat indoDateTimeFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date dateFromString;
        String formattedDate = "";

        try {
            dateFromString = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(stringDate);
            formattedDate = indoDateTimeFormatter.format(dateFromString);
        } catch (ParseException e) {
            System.err.println("Error: Date Parse Exception");
        }

        return formattedDate;
    }
}
