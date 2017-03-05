package pixboh.testforlogin.WebService;

/**
 * Created by PIXBOH on 05/03/2017.
 */

public class ValidOvMyRequest {
    private boolean estfait;
    private String message;

    public ValidOvMyRequest() {
    }

    public ValidOvMyRequest(boolean estFait, String message) {
        this.estfait = estFait;
        this.message = message;
    }

    public boolean isEstfait() {
        return estfait;
    }

    public void setEstfait(boolean estfait) {
        this.estfait = estfait;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
