package gh.gov.moh.admissionsportal.web;

/**
 * Created by molayodecker on 27/01/2017.
 */
public enum Program {
    CERTIFICATE("Certificate- programs","Certificate-program"),
        DIPLOMA("Diploma-programs","Diploma-programs"),
       ADVANCED("Advanced Diploma programs","Certificate program"),
       BACHELOR("Bachelor programs","Certificate program");

    private String name;
    private String values;

    Program(String name, String values) {
        this.name = name;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }
}
