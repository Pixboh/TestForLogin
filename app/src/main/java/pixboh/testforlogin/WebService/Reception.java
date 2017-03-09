package pixboh.testforlogin.WebService;

/**
 * Created by PIXBOH on 08/03/2017.
 */

public class Reception {
    private String reponseCode;
    private String message;


    public Reception(String reponseCode, String message) {
        this.reponseCode = reponseCode;
        this.message = message;
    }

    public String getReponseCode() {
        return reponseCode;
    }

    public void setReponseCode(String reponseCode) {
        this.reponseCode = reponseCode;
    }

    public Reception() {
    }


    public String getMessage() {
        return message;
    }
}
