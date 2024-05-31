package ensah.com.restapi_spring_project.controllers.personnel;


import ensah.com.restapi_spring_project.Dto.Request.proof.CreateRequest;
import ensah.com.restapi_spring_project.Dto.Request.proof.UpdateRequest;
import ensah.com.restapi_spring_project.Dto.Responce.prof.ProfDto;
import ensah.com.restapi_spring_project.models.personnel.Prof;
import ensah.com.restapi_spring_project.services.ProfService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/professor")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class ProfController {

    private final ProfService profService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<ProfDto> getAllProfs() {
        return profService.getAllProfs();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<ProfDto> createNewProf(@RequestBody CreateRequest prof) {
        var profCreated = profService.createProfessor(prof);
        if (profCreated != null){
            return ResponseEntity.ok(profCreated);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<String> deleteProf(@PathVariable Integer id) {
       return profService.deleteProf(id);
    }


}
