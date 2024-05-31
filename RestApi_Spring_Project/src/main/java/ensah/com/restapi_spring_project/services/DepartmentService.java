package ensah.com.restapi_spring_project.services;

import ensah.com.restapi_spring_project.Dto.Request.department.DepartmentRequest;
import ensah.com.restapi_spring_project.Dto.Responce.department.DepartementDto;
import ensah.com.restapi_spring_project.Dto.Responce.field.FieldResponse;
import ensah.com.restapi_spring_project.models.element.Department;
import ensah.com.restapi_spring_project.models.element.Field;
import ensah.com.restapi_spring_project.repositories.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final DepartementRepository departementRepository;

    @Autowired
    public DepartmentService(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    public List<DepartementDto> getAllDepartment() {
        return departementRepository.findAll().stream()
                .map(this::mapToDepartementDto)
                .collect(Collectors.toList());
    }

    private DepartementDto mapToDepartementDto(Department department) {
        return DepartementDto.builder()
                .id(department.getId())
                .departement_name(department.getName())
                .build();
    }

    public ResponseEntity<String> create(DepartmentRequest request) {
            var department = Department.builder()
                    .name(request.getName())
                    .build();
            departementRepository.save(department);
        return ResponseEntity.ok("Department created successfully");

    }
    public ResponseEntity<String> removeDepartment(Integer id) {
        Optional<Department> departmentOptional = departementRepository.findById(id);
        if (departmentOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Department not found with id: " + id);
        }
        try {
            departementRepository.deleteById(id);
            return ResponseEntity.ok("Department removed successfully with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to remove this Department with id: " + id);
        }
    }
    public List<FieldResponse> getAllFieldsByDepartmentId(Integer departmentId) {
        Department department = departementRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        return department.getFields().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private FieldResponse convertToDto(Field field) {
        return FieldResponse.builder()
                .name(field.getName())
                .id(field.getId())
                .build();
    }
}
