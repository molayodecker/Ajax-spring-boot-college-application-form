package gh.gov.moh.admissionsportal.dao;

import gh.gov.moh.admissionsportal.model.Picture;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by molayodecker on 16/02/2017.
 */
@Repository
public interface PictureDao extends CrudRepository<Picture, Long> {
    @Query("select e from Picture e join e.user u where u.id= ?#{principal.id}")
    List<Picture> findAll();
    Picture findById(Long id);
}
