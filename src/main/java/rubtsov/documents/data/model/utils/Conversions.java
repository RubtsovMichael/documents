package rubtsov.documents.data.model.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 29.10.13
 * Time: 15:01
 */
public class Conversions{

    public static Calendar LAST_DATE = new GregorianCalendar(2099, 12, 31);

    public static String stringToViewString(String value) {
        return value == null ? "" : value;
    }

}
