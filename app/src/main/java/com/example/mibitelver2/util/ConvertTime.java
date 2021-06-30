package com.example.mibitelver2.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ConvertTime {

    public static String TextTime(String time) {

        Calendar c = Calendar.getInstance();
        String strDt = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);

        try {
            Date datetime = format.parse(time);
            c.setTime(datetime);

        } catch (Exception e) {
            e.printStackTrace();


        }
        Long k = c.getTimeInMillis() / 1000L;
        Calendar calendar = Calendar.getInstance();
        Long g = calendar.getTimeInMillis() / 1000L;

        int d = (int) (g - k);
        String m = "";
        if (d < 60) {
            m = "Vài giây trước";
        } else {
            if (d >= 60 && d < 60 * 60) {
                m = d / 60 + " phút";
            } else {
                if (d >= 60 * 60 && d < 60 * 60 * 24) {
                    m = d / (60 * 60) + " giờ";
                } else {
                    if (d >= 60 * 60 * 24 && d < 60 * 60 * 24 * 7) {
                        m = d / (60 * 60 * 24) + " tuần";
                    } else {
                        if (d >= 60 * 60 * 24 * 365) {
                            m = d / (60 * 60 * 24 * 365) + " năm";
                        }
                    }
                }
            }

        }
        return m;
    }
}
