package com.sandarovich.utility;


import java.util.Calendar;
import java.util.Date;

public class Utility {

    public static Date getDateByMonthOffset(int month) {
        Date referenceDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(referenceDate);
        c.add(Calendar.MONTH, -month);
        return c.getTime();
    }

}
