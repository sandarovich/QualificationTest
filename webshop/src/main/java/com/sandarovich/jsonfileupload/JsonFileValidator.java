package com.sandarovich.jsonfileupload;


import com.sandarovich.model.JsonFile;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class JsonFileValidator implements Validator {

    private static final Logger logger = Logger.getLogger(JsonFileValidator.class);

    @Override
    public boolean supports(Class<?> paramClass) {
        return JsonFile.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        JsonFile jsonFile = (JsonFile) obj;
        if (isFileEmpty(errors, jsonFile)) return;
        if (isNotValidMediaType(errors, jsonFile)) return;
        if (isNotJsonParsable(errors, jsonFile)) return;
    }

    private boolean isNotJsonParsable(Errors errors, JsonFile jsonFile) {
        try {
            jsonFile.asJsonObject();
        } catch (Exception e) {
            errors.rejectValue("jsonFile", "valid.json");
            logger.error("Unable to parse JSON. Please check it with http://jsonlint.com/", e);
            return true;
        }
        return false;
    }

    private boolean isFileEmpty(Errors errors, JsonFile jsonFile) {
        if (jsonFile.getJsonFile().getSize() == 0) {
            errors.rejectValue("jsonFile", "valid.file");
            return true;
        }
        return false;
    }

    private boolean isNotValidMediaType(Errors errors, JsonFile jsonfile) {
        if (!MediaType.APPLICATION_JSON.equals(MediaType.valueOf(jsonfile.getJsonFile().getContentType()))) {
            errors.rejectValue("jsonFile", "valid.media.type");
            return true;
        }
        return false;
    }
}
