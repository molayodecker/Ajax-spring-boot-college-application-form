package gh.gov.moh.admissionsportal.web;

/**
 * Created by molayodecker on 06/02/2017.
 */
public enum Region {
    UPPEREAST("Upper East", "Upper East"),
    UPPERWEST("Upper West", "Upper West"),
    NORTHERNREGION("Northern Region", "Northern Region"),
    BRONGAHAFO("Brong Ahafo", "Brong Ahafo"),
    WESTERNREGION("Western Region", "Western Region"),
    GREATERACCRAREGION("Greater Accra Region", "Greater Accra Region"),
    CENTRALREGION("Central Region", "Central Region"),
    EASTREGION("Eastern Region", "Eastern Region"),
    VOLTAREGION("Volta Region", "Volta Region");

    private String name;
    private String values;

    Region(String name, String values) {
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
