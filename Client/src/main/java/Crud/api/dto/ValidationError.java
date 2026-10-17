package Crud.api.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends Crud.api.dto.CustomError {
    private List<Crud.api.dto.FieldMessage> errors = new ArrayList<>();
    public void addError(String fieldName, String message) {
       errors.add(new Crud.api.dto.FieldMessage(fieldName, message));
    }
    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<Crud.api.dto.FieldMessage> getErrors() {
        return errors;
    }

}
