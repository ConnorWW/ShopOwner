package com.register;

import android.util.Log;

import java.util.Calendar;

/**
 * Created by apple on 16/5/14.
 */
public class IDGenerator {

    Calendar time;
    int year;
    int month;
    int day;
    int hour;
    int minute;
    int second;
    String ID;

    public IDGenerator(){
        time=Calendar.getInstance();
        year=time.get(Calendar.YEAR);
        month=time.get(Calendar.MONTH)+1;
        day=time.get(Calendar.DAY_OF_MONTH);
        hour=time.get(Calendar.HOUR_OF_DAY);
        minute=time.get(Calendar.MINUTE);
        second=time.get(Calendar.SECOND);
        ID="";
        ID+=Integer.toString(year);
        ID+=Integer.toString(month);
        ID+=Integer.toString(day);
        ID+=Integer.toString(hour);
        ID+=Integer.toString(minute);

        Log.d("IDgenerator",year + "/" + month + "/" + day + " " +hour + ":" +minute+ ":" + second );
    }

    public Calendar getTime() {
        return time;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getID() {
        return ID;
    }
}
