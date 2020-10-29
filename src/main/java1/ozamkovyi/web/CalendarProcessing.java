package ozamkovyi.web;

import ozamkovyi.db.bean.CreditCardBean;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Class for date processing
 *
 * @author O.Zamkovyi
 */

public class CalendarProcessing {

    /**
     * Cast string to date in format YYYY-MM_DD
     *
     * @param dateInString string for casting
     * @return Calendar object
     */
    public static Calendar string2Date(String dateInString) {
        Calendar date = new GregorianCalendar();
        int month = Integer.parseInt(dateInString.substring(5, 7)) - 1;
        date.set(Integer.parseInt(dateInString.substring(0, 4)), month, Integer.parseInt(dateInString.substring(8, 10)));
        return date;
    }

    /**
     * Cast date ro string in format YYYY-MM_DD
     *
     * @param date Calendar object for casting
     * @return String object
     */
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

    /**
     * Cast date to string in format YYYY-MM
     *
     * @param calendar Calendar object for casting
     * @return String object
     */

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

    /**
     * Cast string to date in format YYYY-MM-DD HH-MM-SS
     *
     * @param date String object for casting
     * @return Calendar object
     */
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

    /**
     * Cast date to string in format YYYY-MM-DD HH-MM-SS
     *
     * @param date Calendar object for casting
     * @return String object
     */
    public static String fullDate2String(Calendar date) {
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

    /**
     * Returns validity for new card
     * The card is valid for 2 years
     *
     * @return String validity
     */
    public static String getValidityForNewCard() {
        Calendar calendar = Calendar.getInstance();
        StringBuilder sb = new StringBuilder();
        sb.append((calendar.get(Calendar.YEAR) + 2) + "-");
        sb.append((calendar.get(Calendar.MONTH) + 1) + "-");
        sb.append("01");
        return sb.toString();
    }

    /**
     * Returns current date in format YYYY-MM-DD
     *
     * @return String object
     */
    public static String getFullCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return fullDate2String(calendar);
    }

    /**
     * Returns true if credit card is valid
     * return false if credit card is not valid
     *
     * @param creditCard current credit card
     * @return true if credit card is valid or false if credit card is not valid
     */
    public static boolean isCardValid(CreditCardBean creditCard) {
        Calendar calendar = Calendar.getInstance();
        int creditCardYear = creditCard.getValidity().get(Calendar.YEAR);
        int creditCardMouth = creditCard.getValidity().get(Calendar.MONTH);

        if (calendar.get(Calendar.YEAR) > creditCardYear) {
            return false;
        }
        if (calendar.get(Calendar.YEAR) < creditCardYear) {
            return true;
        }
        if (calendar.get(Calendar.MONTH) < creditCardMouth) {
            return true;
        }
        return false;


    }
}
