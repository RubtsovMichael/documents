package rubtsov.documents.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import rubtsov.documents.data.model.dto.FileDto;

/**
 * Created by mrubtsov on 19.12.13.
 */
public class FileValidator implements Validator {

    @Override
    public boolean supports(Class<?> arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void validate(Object uploadedFile, Errors errors) {

        FileDto file = (FileDto) uploadedFile;

        if (file.getFile().getSize() == 0) {
            errors.rejectValue("file", "uploadForm.selectFile",
                    "Please select a file!");
        }

    }


}
