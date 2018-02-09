package com.example.admin.stalyzer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by admin on 04-01-2018.
 */

public class GetDate
{
    public static String getCurrentDate()
    {
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");

        Date todayDate = new Date();
        return (currentDate.format(todayDate).replaceAll("/"," "));
//        showToast(thisDate);
    }

    public static String getTomorrowDate()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(tomorrow).replace("/"," ");
    }

    public static String getDayAfterTomorrowDate()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        Date tomorrow = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(tomorrow).replace("/"," ");
    }

    public static String getYesterdayDate()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date tomorrow = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(tomorrow).replace("/"," ");
    }

    public static String getNumberOfDaysOfCurrentMonth()
    {
        return Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH)+"";
    }
}
