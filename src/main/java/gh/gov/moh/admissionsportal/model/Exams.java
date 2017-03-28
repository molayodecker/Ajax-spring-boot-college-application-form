package gh.gov.moh.admissionsportal.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by molayodecker on 28/01/2017.
 */
@Entity
public class Exams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The above field must not be blank.")
    @Size(min = 2, max = 25)
    private String examType;

    @NotNull(message = "The above field must not be blank.")
    @Size(min = 2, max = 25)
    private String subject;

    @NotNull(message = "The above field must not be blank.")
    @Size(min = 2, max = 25)
    private String grade;

    @NotNull(message = "The above field must not be blank.")
    private Long indexNumber;

    @NotNull(message = "The above field must not be blank.")
    private Long gradeYear;

    private boolean isComplete;

/*    private int counter;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }*/

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = "WASSCE";
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Long getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(Long indexNumber) {
        this.indexNumber = indexNumber;
    }

    public Long getGradeYear() {
        return gradeYear;
    }

    public void setGradeYear(Long gradeYear) {
        this.gradeYear = gradeYear;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
