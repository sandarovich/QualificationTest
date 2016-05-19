package com.sandarovich.fileupload.validaton;


import com.sandarovich.fileupload.model.File;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FileValidator implements Validator {

    @Override
    public boolean supports(Class<?> paramClass) {
        return File.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        File file = (File) obj;
        if (file.getFile().getSize() == 0) {
            errors.rejectValue("file", "valid.file");
        }
        if (!MediaType.APPLICATION_JSON.equals(MediaType.valueOf(file.getFile().getContentType()))) {
            errors.rejectValue("file", "media.type.file");
        }

    }
}
