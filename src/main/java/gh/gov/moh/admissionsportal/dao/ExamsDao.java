package gh.gov.moh.admissionsportal.dao;

import gh.gov.moh.admissionsportal.model.Exams;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by molayodecker on 28/01/2017.
 */
@Repository
public interface ExamsDao extends CrudRepository<Exams,Long>{
    @Query("select e from Exams e join e.user u where u.id= ?#{principal.id}")
    List<Exams> findAll();
}
