package ensah.com.restapi_spring_project.controllers.element;


import ensah.com.restapi_spring_project.models.element.Level;
import ensah.com.restapi_spring_project.services.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/api/level")
@PreAuthorize("hasRole('ADMIN')")
public class LevelController {

    private final LevelService levelService;

    @Autowired
    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }


    @GetMapping("/levels")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<Level> getAllLeveles() {

        return levelService.getAllLevels();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('admin:read')")
    public void createNewLevel(Level level) {

         levelService.save(level);
    }



}
