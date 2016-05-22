package com.sandarovich.validator;


import com.google.gson.JsonParseException;
import com.sandarovich.model.UploadForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UploadFormValidator implements Validator {

    private static final Logger logger = LoggerFactory.getLogger(UploadFormValidator.class);

    private static final String REJECT_MESSAGE_KEY = "jsonFile";

    @Override
    public boolean supports(Class<?> paramClass) {
        return UploadForm.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        UploadForm uploadForm = (UploadForm) obj;
        if (isFileEmpty(errors, uploadForm)) return;
        if (isNotValidMediaType(errors, uploadForm)) return;
        if (IsNotValidJSON(errors, uploadForm)) return;
    }

    private boolean IsNotValidJSON(Errors errors, UploadForm uploadForm) {
        try {
            uploadForm.asJsonObject();
        } catch (JsonParseException e) {
            errors.rejectValue(REJECT_MESSAGE_KEY, "valid.json");
            logger.error("Unable to parse JSON. Please check it with http://jsonlint.com/", e);
            return true;
        }
        return false;
    }

    private boolean isFileEmpty(Errors errors, UploadForm uploadForm) {
        if (uploadForm.getJsonFile().getSize() == 0) {
            errors.rejectValue(REJECT_MESSAGE_KEY, "valid.file");
            return true;
        }
        return false;
    }

    private boolean isNotValidMediaType(Errors errors, UploadForm uploadForm) {
        if (!MediaType.APPLICATION_JSON.equals(MediaType.valueOf(uploadForm.getJsonFile().getContentType()))) {
            errors.rejectValue(REJECT_MESSAGE_KEY, "valid.media.type");
            return true;
        }
        return false;
    }
}
