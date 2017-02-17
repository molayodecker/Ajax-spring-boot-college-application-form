package gh.gov.moh.admissionsportal.dao;

import gh.gov.moh.admissionsportal.model.School;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by molayodecker on 07/02/2017.
 */
@Repository
public interface SchoolDao extends CrudRepository<School, Long>{
    @Query("select e from School e join e.user u where u.id= ?#{principal.id}")
    List<School> findAll();
}
