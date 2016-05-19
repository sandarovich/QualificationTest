package com.sandarovich.fileupload.validation;


import com.sandarovich.fileupload.model.File;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FileValidator implements Validator {

    private static final Logger logger = Logger.getLogger(FileValidator.class);

    @Override
    public boolean supports(Class<?> paramClass) {
        return File.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        File file = (File) obj;
        if (isFileEmpty(errors, file)) return;
        if (isNotValidMediaType(errors, file)) return;
        if (isNotJsonParsable(errors, file)) return;
    }

    private boolean isNotJsonParsable(Errors errors, File file) {
        try {
            file.asJsonObject();
        } catch (Exception e) {
            errors.rejectValue("file", "valid.json");
            logger.error("Unable to parse JSON. Please check it with http://jsonlint.com/" + file.getFile().getName(), e);
            return true;
        }
        return false;
    }

    private boolean isFileEmpty(Errors errors, File file) {
        if (file.getFile().getSize() == 0) {
            errors.rejectValue("file", "valid.file");
            return true;
        }
        return false;
    }

    private boolean isNotValidMediaType(Errors errors, File file) {
        if (!MediaType.APPLICATION_JSON.equals(MediaType.valueOf(file.getFile().getContentType()))) {
            errors.rejectValue("file", "valid.media.type");
            return true;
        }
        return false;
    }
}
