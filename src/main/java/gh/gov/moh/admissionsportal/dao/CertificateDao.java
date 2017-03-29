package gh.gov.moh.admissionsportal.dao;

import gh.gov.moh.admissionsportal.model.CertificateProgramme;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by molayodecker on 27/01/2017.
 */

@Repository
public interface CertificateDao extends CrudRepository<CertificateProgramme, Long> {
    @Query("select t from CertificateProgramme t where t.user.id=?#{principal.id}")
    List<CertificateProgramme> findAll();

    @Query("select t from CertificateProgramme t where t.user.id=?#{principal.id}")
    CertificateProgramme ListAll();

    //update CertificateProgramme ear set ear.flag = ?1 where ear.user.id=?2
    //update CertificateProgramme ear set ear.flag = ?#{'FALSE'} where ear.user.id=?#{principal.id}
    @Modifying
    @Query("update CertificateProgramme ear set ear.flag = TRUE where ear.user.id=?#{principal.id}")
    int falseFlagger(Long id);
}
