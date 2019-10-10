package kan10.common;

import java.sql.Timestamp;

/**
 * Class : Random Dates for each month
 * @author Mouad, Suriya
 */
public class RandomDates {

    // 2017 year
    public static Timestamp RandomTimeStampDateLastYear() {
        long offset = Timestamp.valueOf("2017-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2017-12-31 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long) (Math.random() * diff));
        return rand;
    }

    // 2018 year
    public static Timestamp RandomTimeStampDate() {
        long offset = Timestamp.valueOf("2018-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2018-12-31 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long) (Math.random() * diff));
        return rand;
    }

    public static Timestamp RandomTimeStampDateJanuary () {
        long offset = Timestamp.valueOf("2018-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2018-01-31 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long) (Math.random() * diff));
        return rand;
    }

    public static Timestamp RandomTimeStampDateFebruary () {
        long offset = Timestamp.valueOf("2018-02-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2018-02-31 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long) (Math.random() * diff));
        return rand;
    }

    public static Timestamp RandomTimeStampDateMarch () {
        long offset = Timestamp.valueOf("2018-03-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2018-03-31 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long) (Math.random() * diff));
        return rand;
    }

    public static Timestamp RandomTimeStampDateApril () {
        long offset = Timestamp.valueOf("2018-04-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2018-04-31 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long) (Math.random() * diff));
        return rand;
    }

    public static Timestamp RandomTimeStampDateMay () {
        long offset = Timestamp.valueOf("2018-05-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2018-05-31 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long) (Math.random() * diff));
        return rand;
    }

    public static Timestamp RandomTimeStampDateJune () {
        long offset = Timestamp.valueOf("2018-06-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2018-06-31 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long) (Math.random() * diff));
        return rand;
    }

    public static Timestamp RandomTimeStampDateJuly () {
        long offset = Timestamp.valueOf("2018-07-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2018-07-31 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long) (Math.random() * diff));
        return rand;
    }

    public static Timestamp RandomTimeStampDateAugust () {
        long offset = Timestamp.valueOf("2018-08-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2018-08-31 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long) (Math.random() * diff));
        return rand;
    }

    public static Timestamp RandomTimeStampDateSeptember() {
        long offset = Timestamp.valueOf("2018-09-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2018-09-31 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long) (Math.random() * diff));
        return rand;
    }

    public static Timestamp RandomTimeStampDateOctober () {
        long offset = Timestamp.valueOf("2018-10-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2018-10-31 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long) (Math.random() * diff));
        return rand;
    }

    public static Timestamp RandomTimeStampDateNovember () {
        long offset = Timestamp.valueOf("2018-11-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2018-11-31 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long) (Math.random() * diff));
        return rand;
    }

    public static Timestamp RandomTimeStampDateDecember () {
        long offset = Timestamp.valueOf("2018-12-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2018-12-31 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long) (Math.random() * diff));
        return rand;
    }

}
