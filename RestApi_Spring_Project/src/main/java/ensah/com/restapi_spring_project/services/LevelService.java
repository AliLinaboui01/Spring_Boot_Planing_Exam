package ensah.com.restapi_spring_project.services;

import ensah.com.restapi_spring_project.models.element.Level;
import ensah.com.restapi_spring_project.repositories.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelService {
    private final LevelRepository levelRepository;


    @Autowired
    public LevelService(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    public List<Level> getAllLevels() {

        return  levelRepository.findAll();
    }


    public void save(Level level) {
        levelRepository.save(level);
    }
}
