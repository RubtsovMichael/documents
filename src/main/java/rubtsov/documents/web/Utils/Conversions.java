package rubtsov.documents.web.Utils;

import rubtsov.documents.data.model.CaseFolder;
import rubtsov.documents.web.dto.CaseFolderDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 29.10.13
 * Time: 15:01
 */
public class Conversions{

    public static List<CaseFolderDto> caseFoldersToDtos(List<CaseFolder> entities) {
        ArrayList<CaseFolderDto> dtos = new ArrayList<>(entities.size());

        for (CaseFolder entity : entities) {
            CaseFolderDto dto = new CaseFolderDto(entity);
            dtos.add(dto);
        }

        return dtos;
    }

}
