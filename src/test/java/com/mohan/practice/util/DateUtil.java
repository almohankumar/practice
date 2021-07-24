package com.mohan.practice.util;

import com.github.javafaker.Faker;

import com.mohan.practice.enums.Timeline;
import lombok.extern.log4j.Log4j2;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Log4j2
public class DateUtil {

    public static Faker faker;
    private static DateTimeFormatter formatter;
    private static int range = 1;


    static {

        faker = new Faker();

    }

    private DateUtil(){

    }


    public static String getUniqueTimeStampAsString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmssSSS");
        return LocalDateTime.now().format(formatter);

    }

    public static String getUniqueTimeStampAsString(int days, Timeline timeline) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmssSSS");


        if (timeline == Timeline.PAST) {

            return LocalDateTime.now().minusDays(days).format(formatter);

        } else if (timeline == Timeline.FUTURE) {

            return LocalDateTime.now().plusDays(days).format(formatter);

        } else {

            return LocalDateTime.now().format(formatter);

        }

    }

    public static String dateToString(Date date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return dateFormat.format(date);
    }



    public static String usToSqlConvert(String usDate) {
        String seperator = "-";
        String sqlDate;
        String[] date = usDate.split("/");
        String month = date[0];
        String day = date[1];
        String year = date[2];
        sqlDate = year + seperator + month + seperator + day;
        log.info("Converted date is:" + sqlDate);
        return sqlDate;

    }

    public static java.sql.Date getSqlDate(String date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date utilDate = null;
        try {
            utilDate = dateFormat.parse(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(utilDate!=null){
            return new java.sql.Date(utilDate.getTime());
        }else{
            return null;
        }

    }


    public static java.sql.Date getSqlDateFromLocalDate(LocalDate localDate){
        return java.sql.Date.valueOf(localDate);
    }

    public static java.sql.Time getSqlTime(String time) {

        return java.sql.Time.valueOf(time);

    }

    public static LocalDateTime getLocalDateTimeFromTimestamp(Timestamp t){

        return t.toLocalDateTime();
    }

    public static String getDateAfterCurrentDate(String days) {

        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime then = now.plusDays(Integer.parseInt(days));
        return then.format(format);

    }

    private static void setRange(int min, int max) {

        range = faker.number().numberBetween(min,max);

    }

    public static String getCurrentDate(){

        LocalDate localDate = LocalDate.now();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = localDate.format(formatter);
        log.info("Generated Current Date:"+date);

        return date;

    }

    public static String getCurrentDateTime(){

        LocalDateTime localDateTime = LocalDateTime.now();
        formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy:HH:MM:SS");
        String date = localDateTime.format(formatter);
        log.info("Generated Current Date Time:"+date);

        return date;

    }

    public static String getCurrentDateTimeOfPattern(String pattern){

        LocalDateTime localDateTime = LocalDateTime.now();
        formatter = DateTimeFormatter.ofPattern(pattern);
        String date = localDateTime.format(formatter);
        log.info("Generated Current Date Time:"+date+" of pattern "+pattern);

        return date;

    }

    public static String getCurrentDateOfPattern(String pattern){

        LocalDate localDate = LocalDate.now();
        formatter = DateTimeFormatter.ofPattern(pattern);
        String date = localDate.format(formatter);
        log.info("Generated Current Date:"+date+" of pattern "+pattern);

        return date;

    }

    public static String getPastDateTimeOfPatternMinusDays(String pattern, int days){

        LocalDateTime localDateTime = LocalDateTime.now().minusDays(days);
        formatter = DateTimeFormatter.ofPattern(pattern);
        String date = localDateTime.format(formatter);
        log.info("Generated Past Date Time:"+date+" Format:"+pattern);

        return date;

    }

    public static String getFutureDateTimeOfPatternWithin(String pattern, int min, int max){

        setRange(min,max);
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(range);
        formatter = DateTimeFormatter.ofPattern(pattern);
        String date = localDateTime.format(formatter);
        log.info("Generated Future Date Time:"+date+" Format:"+pattern);

        return date;

    }

    public static String getPastDateOfPatternMinusDays(String pattern, int days){

        LocalDate localDate = LocalDate.now().minusDays(days);
        formatter = DateTimeFormatter.ofPattern(pattern);
        String date = localDate.format(formatter);
        log.info("Generated Past Date:"+date+" Format:"+pattern);

        return date;

    }

    public static String getFutureDateOfPatternPlusDays(String pattern, int days){

        LocalDate localDate = LocalDate.now().plusDays(days);
        formatter = DateTimeFormatter.ofPattern(pattern);
        String date = localDate.format(formatter);
        log.info("Generated Future Date:"+date+" Format:"+pattern);

        return date;

    }

    private static String getPastDateInRange(int min, int max){

        setRange(min,max);

        LocalDate localDate = LocalDate.now().minusDays(range);
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = localDate.format(formatter);
        log.info("Generated Past Date:"+date);
        return date;

    }

    public static String getFutureDateWithin(int min, int max){

        setRange(min,max);

        LocalDate localDate = LocalDate.now().plusDays(range);
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = localDate.format(formatter);
        log.info("Generated Future Date:"+date);
        return date;

    }

}
