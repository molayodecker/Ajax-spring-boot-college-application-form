package gh.gov.moh.admissionsportal.model;

import javax.persistence.*;

/**
 * Created by molayodecker on 07/02/2017.
 */
@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String programChoice;

    private String schoolChoice;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProgramChoice() {
        return programChoice;
    }

    public void setProgramChoice(String programChoice) {
        this.programChoice = programChoice;
    }

    public String getSchoolChoice() {
        return schoolChoice;
    }

    public void setSchoolChoice(String schoolChoice) {
        this.schoolChoice = schoolChoice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
