package ozamkovyi.web;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarProcessing {


    public static Calendar string2Date(String dateInString) {
        Calendar date = new GregorianCalendar();
        int day = Integer.parseInt(dateInString.substring(5, 7)) - 1;
        date.set(Integer.parseInt(dateInString.substring(0, 4)), day, Integer.parseInt(dateInString.substring(8, 10)));
        return date;
    }

    public static String date2String(Calendar date) {
        StringBuilder sb = new StringBuilder();
        sb.append(date.get(Calendar.YEAR) + "-");
        int a = date.get(Calendar.MONTH) + 1;
        String s = Integer.toString(a);
        if (s.length() == 1) {
            sb.append("0" + s + "-");
        } else {
            sb.append(s + "-");
        }
        s = date.get(Calendar.DAY_OF_MONTH) + "";
        if (s.length() == 1) {
            sb.append("0" + s);
        } else {
            sb.append(s);
        }
        return sb.toString();
    }

    public static String date2StringForCard(Calendar calendar) {
        StringBuilder sb = new StringBuilder();
        sb.append(calendar.get(Calendar.YEAR) + "-");
        int a = calendar.get(Calendar.MONTH) + 1;
        String s = Integer.toString(a);
        if (s.length() == 1) {
            sb.append("0" + s);
        } else {
            sb.append(s);
        }
        return sb.toString();
    }

    public static Calendar string2FullDate(String date) {
        Calendar calendar = new GregorianCalendar();
        int day = Integer.parseInt(date.substring(5, 7)) - 1;
        int year = Integer.parseInt(date.substring(0, 4));
        int mounth = Integer.parseInt(date.substring(8, 10));
        int hour = Integer.parseInt(date.substring(11, 13));
        int minute = Integer.parseInt(date.substring(14, 16));
        int second = Integer.parseInt(date.substring(17, 19));

        calendar.set(year, day, mounth, hour, minute, second);
        return calendar;

    }

    public static String allDate2String(Calendar date) {
        StringBuilder sb = new StringBuilder();
        sb.append(date.get(Calendar.YEAR) + "-");
        int a = date.get(Calendar.MONTH) + 1;
        String s = Integer.toString(a);
        if (s.length() == 1) {
            sb.append("0" + s + "-");
        } else {
            sb.append(s + "-");
        }
        s = date.get(Calendar.DAY_OF_MONTH) + " ";
        if (s.length() == 2) {
            sb.append("0" + s);
        } else {
            sb.append(s);
        }
        s = date.get(Calendar.HOUR_OF_DAY) + ":";
        if (s.length() == 2) {
            sb.append("0" + s);
        } else {
            sb.append(s);
        }
        s = date.get(Calendar.MINUTE) + ":";
        if (s.length() == 2) {
            sb.append("0" + s);
        } else {
            sb.append(s);
        }
        s = date.get(Calendar.SECOND) + "";
        if (s.length() == 1) {
            sb.append("0" + s);
        } else {
            sb.append(s);
        }
        return sb.toString();
    }

    public static String getValidityForNewCard(){
        Calendar calendar = Calendar.getInstance();
        StringBuilder sb = new StringBuilder();
        sb.append((calendar.get(Calendar.YEAR)+2)+"-");
        sb.append((calendar.get(Calendar.MONTH)+1)+"-");
        sb.append("01");
        return sb.toString();
    }
}
