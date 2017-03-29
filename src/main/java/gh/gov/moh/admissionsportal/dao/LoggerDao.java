package gh.gov.moh.admissionsportal.dao;

import gh.gov.moh.admissionsportal.model.UrlLogger;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by molayodecker on 10/03/2017.
 */
@Repository
public interface LoggerDao extends CrudRepository<UrlLogger, Long> {
    @Query("select t from UrlLogger t where t.user.id=?#{principal.id}")
    @Transactional
    List<UrlLogger> findAll();

    @Query("select t from UrlLogger t where t.user.id=?#{principal.id}")
    @Transactional
    UrlLogger ListAll();

    //@Query("select t from Logger t where t.redirect IS EMPTY AND t.user.id IS EMPTY ")
    //Boolean Empty();

    //@Query(value = "insert into commit_activity_link (commit_id, activity_id) VALUES (?1, ?2)", nativeQuery = true)
    @Modifying
    @Query(value = "insert into UrlLogger (redirect,user_id) VALUES (:insertLink,:id)", nativeQuery = true)
    @Transactional
    void logURI(@Param("insertLink") String insertLink, @Param("id") Long id);

    @Modifying
    @Query("update UrlLogger ear set ear.redirect =:link where ear.user.id=?#{principal.id}")
    @Transactional
    void log(@Param("link") String link);
}
