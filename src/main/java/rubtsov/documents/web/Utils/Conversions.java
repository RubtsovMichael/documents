package rubtsov.documents.web.Utils;

import rubtsov.documents.data.model.CaseFolder;
import rubtsov.documents.web.dto.CaseFolderDto;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 29.10.13
 * Time: 15:01
 */
public class Conversions{

    public static Calendar LAST_DATE = new GregorianCalendar(2099, 12, 31);

    public static List<CaseFolderDto> caseFoldersToDtos(List<CaseFolder> entities) {
        ArrayList<CaseFolderDto> dtos = new ArrayList<>(entities.size());

        for (CaseFolder entity : entities) {
            CaseFolderDto dto = new CaseFolderDto(entity);
            dtos.add(dto);
        }

        return dtos;
    }

    public static String stringToViewString(String value) {
        return value == null ? "" : value;
    }

    public static Calendar dateToViewCalendar(Date value) {
        if (value == null) {
            return LAST_DATE;
        } else {
            Calendar result = new GregorianCalendar();
            result.setTime(value);
            return result;
        }
    }

    public static Date viewCalendarToDate(Calendar value) {
        if (LAST_DATE.equals(value)) {
            return null;
        } else {
            return new Date(value.getTimeInMillis());
        }
    }

}
