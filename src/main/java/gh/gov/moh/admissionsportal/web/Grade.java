package gh.gov.moh.admissionsportal.web;

/**
 * Created by molayodecker on 30/01/2017.
 */
public enum Grade {
    GRADEA1("A1", "A1"),
    GRADEB2("B1", "B2"),
    GRADEB3("B3", "B3"),
    GRADEC4("C4", "C4"),
    GRADEC5("C5", "C5"),
    GRADEC6("C6", "C6"),
    GRADED7("D7", "D7"),
    GRADEE8("E8", "E8");

    private String name;
    private String values;

    Grade(String name, String values) {
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
