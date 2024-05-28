package ensah.com.restapi_spring_project.controllers.element;


import ensah.com.restapi_spring_project.Dto.Responce.DepartementDto;
import ensah.com.restapi_spring_project.models.element.Department;
import ensah.com.restapi_spring_project.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class DepartementController {
    private final DepartmentService departementService;

    @Autowired
    public DepartementController(DepartmentService departementService) {
        this.departementService = departementService;
    }

    @GetMapping("/departements")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<DepartementDto> getAllDepartment() {
        return departementService.getAllDepartment();
    }


    @PostMapping("/departements")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<String>  createNewDepartment(@Valid @RequestBody Department department) {
        return departementService.save(department);
    }


    @DeleteMapping("/departements/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<String> removeDepartment(@PathVariable Integer id) {
       return departementService.removeDepartment(id);
    }



}
