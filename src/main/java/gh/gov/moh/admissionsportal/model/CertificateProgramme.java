package gh.gov.moh.admissionsportal.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by molayodecker on 25/01/2017.
 * @Pattern(regexp = "#[a-zA-Z0-9_.]{1,}@[a-zA-Z]{2,}[\\\\.][a-zA-Z]{3}|[a-zA-Z]{3}")
 */

@Entity
public class CertificateProgramme {

        @OneToOne
        @JoinColumn(name = "user_id")
        private User user;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        /*@Lob
        private byte[] bytes;*/

        @NotNull(message = "Please enter your fullname .")
        @Size(min = 2, max = 50)
        private String fullName;

        @NotNull(message = "The above field must not be blank.")
        private String gender;

        //@NotNull(message = "The above field must not be blank.")
       // @DateTimeFormat(pattern="dd/MM/yyyy")
       // @Past
        private Date date;

        @NotNull(message = "The above field must not be blank.")
        @Size(min = 2, max = 50)
        private String Nationality;

        @NotNull(message = "The above field must not be blank.")
        @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
                +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
                +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
                message="Please enter a valid email address")
        private String email;

        @NotNull(message = "The above field must not be blank.")
        private String married;

        @NotNull(message = "The above field must not be blank.")
        @Size(min = 2, max = 100)
        private String postalAddress;

        @NotNull(message = "The above field must not be blank.")
        @Size(min = 2, max = 50)
        private String homeTown;

        @NotNull(message = "The above field must not be blank.")
        private String region;

        @NotNull(message = "The above field must not be blank.")
        private Long telephoneNumber;

        @NotNull(message = "The above field must not be blank.")
        @Size(min = 2, max = 25)
        private String language;

        private String guardianFullName;

        private Long guardianTelephoneNumber;

        public User getUser() {
                return user;
        }

        public void setUser(User user) {
                this.user = user;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getFullName() {
                return fullName;
        }

        public void setFullName(String fullName) {
                this.fullName = fullName;
        }

        public String getGender() {
                return gender;
        }

        public void setGender(String gender) {
                this.gender = gender;
        }

        public Date getDate() {
                return date;
        }

        public void setDate(Date date) {
                this.date = date;
        }

        public String getNationality() {
                return Nationality;
        }

        public void setNationality(String nationality) {
                Nationality = nationality;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getMarried() {
                return married;
        }

        public void setMarried(String married) {
                this.married = married;
        }

        public String getPostalAddress() {
                return postalAddress;
        }

        public void setPostalAddress(String postalAddress) {
                this.postalAddress = postalAddress;
        }

        public String getHomeTown() {
                return homeTown;
        }

        public void setHomeTown(String homeTown) {
                this.homeTown = homeTown;
        }

        public String getRegion() {
                return region;
        }

        public void setRegion(String region) {
                this.region = region;
        }

        public Long getTelephoneNumber() {
                return telephoneNumber;
        }

        public void setTelephoneNumber(Long telephoneNumber) {
                this.telephoneNumber = telephoneNumber;
        }

        public String getLanguage() {
                return language;
        }

        public void setLanguage(String language) {
                this.language = language;
        }

        public String getGuardianFullName() {
                return guardianFullName;
        }

        public void setGuardianFullName(String guardianFullName) {
                this.guardianFullName = guardianFullName;
        }

        public Long getGuardianTelephoneNumber() {
                return guardianTelephoneNumber;
        }

        public void setGuardianTelephoneNumber(Long guardianTelephoneNumber) {
                this.guardianTelephoneNumber = guardianTelephoneNumber;
        }
}
