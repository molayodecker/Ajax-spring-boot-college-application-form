package gh.gov.moh.admissionsportal.model;

/**
 * Created by molayodecker on 19/03/2017.
 */
public class ErrorMessage {
    private String fieldName;
    private String message;

    public ErrorMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }
    public String getFieldName() {
        return fieldName;
    }
    public String getMessage() {
        return message;
    }

}
