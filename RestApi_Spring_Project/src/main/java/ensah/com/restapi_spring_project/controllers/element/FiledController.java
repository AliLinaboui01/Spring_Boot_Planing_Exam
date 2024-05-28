package ensah.com.restapi_spring_project.controllers.element;


import ensah.com.restapi_spring_project.Dto.Responce.FiledDto;
import ensah.com.restapi_spring_project.Dto.Responce.field.FieldResponse;
import ensah.com.restapi_spring_project.Dto.request.field.CreateFieldDto;
import ensah.com.restapi_spring_project.services.FiledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/admin/field")
@PreAuthorize("hasRole('ADMIN')")
public class FiledController {

    private final FiledService filedService;

    @Autowired
    public FiledController(FiledService filedService) {
        this.filedService = filedService;
    }


    @GetMapping("/allFields")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<FiledDto> getAll() {
        return filedService.getAllFiled();
    }
    @PostMapping("/createField")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<FieldResponse> create(@RequestBody CreateFieldDto filedDto){
        return ResponseEntity.ok(filedService.createField(filedDto));
    }
}
