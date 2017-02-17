package gh.gov.moh.admissionsportal.service;

import gh.gov.moh.admissionsportal.model.Picture;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by molayodecker on 16/02/2017.
 */
public interface PictureService {
    List<Picture> findAll();
    Picture findById(Long id);
    void save(Picture picture, MultipartFile file);
    void delete(Picture picture);
}
