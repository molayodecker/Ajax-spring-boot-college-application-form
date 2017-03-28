package gh.gov.moh.admissionsportal.model;

import java.util.List;

/**
 * Created by molayodecker on 18/03/2017.
 */

public class JsonResponse {
    private String status;
    private List<ErrorMessage> errorMessageList;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<ErrorMessage> getErrorMessageList() {
        return this.errorMessageList;
    }
    public void setErrorMessageList(List<ErrorMessage> errorMessageList) {
        this.errorMessageList = errorMessageList;
    }
}
