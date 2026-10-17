package dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidadetionError extends CustomError{
    private List<FieldMessage> errors = new ArrayList<>();
    public void addError(String fieldName, String message) {
       errors.add(new FieldMessage (fieldName, message));
    }
    public ValidadetionError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

}
