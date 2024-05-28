package ensah.com.restapi_spring_project.controllers.personnel;


import ensah.com.restapi_spring_project.Dto.Responce.ProfDto;
import ensah.com.restapi_spring_project.models.personnel.Prof;
import ensah.com.restapi_spring_project.services.ProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class ProfController {

    private final ProfService profService;

    @Autowired
    public ProfController(ProfService profService) {
        this.profService = profService;
    }


    @GetMapping("/profs")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<ProfDto> getAllProfs() {
        return profService.getAllProfs();
    }


    // this function is if we want to have register prof with all info firstname ... and
    // after that we insert all in user
    @PostMapping("profs/new")
    @PreAuthorize("hasAuthority('admin:create')")
    public void createProf(@RequestBody Prof profDetails) {
       profService.createProf(profDetails);

    }

    @PostMapping("/profs")
    @PreAuthorize("hasAuthority('admin:create')")
    public void createNewProf(Prof prof) {
         profService.save(prof);
    }

    @PutMapping("profs/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public void updateProf(@PathVariable Integer id, @RequestBody Prof profDetails) {
        profService.updateProf(id, profDetails);
    }


    @DeleteMapping("profs/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<Void> deleteProf(@PathVariable Integer id) {
        profService.deleteProf(id);
        return ResponseEntity.noContent().build();
    }


}
