package gh.gov.moh.admissionsportal.service;

import gh.gov.moh.admissionsportal.dao.PictureDao;
import gh.gov.moh.admissionsportal.model.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by molayodecker on 16/02/2017.
 */
@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureDao pictureDao;

    @Override
    public List<Picture> findAll() {
        return pictureDao.findAll();
    }

    @Override
    public Picture findById(Long id) {
        return pictureDao.findById(id);
    }

    @Override
    public void save(Picture picture, MultipartFile file) {
        try {
            //picture.setFileName(file.getOriginalFilename());
            picture.setBytes(file.getBytes());
            pictureDao.save(picture);
        } catch (IOException e) {
            System.err.println("Unable to get byte array from Uploaded file");
        }
    }

    @Override
    public void delete(Picture picture) {
        pictureDao.delete(picture);
    }
}
