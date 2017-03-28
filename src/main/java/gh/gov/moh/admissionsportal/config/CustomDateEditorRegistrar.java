package gh.gov.moh.admissionsportal.config;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by molayodecker on 23/03/2017.
 */
public class CustomDateEditorRegistrar implements PropertyEditorRegistrar
{
    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry)
    {
        registry.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), true));
    }
}
