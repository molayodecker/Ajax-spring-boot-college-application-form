package gh.gov.moh.admissionsportal.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by molayodecker on 07/02/2017.
 */
@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Please select a program choice.")
    @Size(min = 2, max = 25)
    private String programChoice;

    @NotNull(message = "Please select a school choice.")
    @Size(min = 2, max = 25)
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
