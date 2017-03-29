package gh.gov.moh.admissionsportal.model;

import javax.persistence.*;

/**
 * Created by molayodecker on 10/03/2017.
 */
@Entity
public class UrlLogger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String redirect;
    //private String lastVisited;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
