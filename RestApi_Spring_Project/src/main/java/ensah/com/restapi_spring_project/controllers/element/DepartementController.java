package ensah.com.restapi_spring_project.controllers.element;


import ensah.com.restapi_spring_project.Dto.Request.department.DepartmentRequest;
import ensah.com.restapi_spring_project.Dto.Responce.department.DepartementDto;
import ensah.com.restapi_spring_project.Dto.Responce.field.FieldResponse;
import ensah.com.restapi_spring_project.models.element.Department;
import ensah.com.restapi_spring_project.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/department")
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


    @PostMapping("/create")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<String>  createNewDepartment(@Valid @RequestBody DepartmentRequest department) {
        return departementService.create(department);
    }


    @DeleteMapping("/departements/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<String> removeDepartment(@PathVariable Integer id) {
       return departementService.removeDepartment(id);
    }

    @GetMapping("/{idDept}/fields")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<FieldResponse>> getAllFieldsByIdDept(@PathVariable Integer idDept){
        List<FieldResponse> fieldList = departementService.getAllFieldsByDepartmentId(idDept);
        return ResponseEntity.ok(fieldList);
    }


}
