package ensah.com.restapi_spring_project.controllers.exam;

import ensah.com.restapi_spring_project.Dto.Responce.exam.ExamTypeResponse;
import ensah.com.restapi_spring_project.models.exam.ExamType;
import ensah.com.restapi_spring_project.services.ExamTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/api/exam-type")
@PreAuthorize("hasRole('ADMIN')")
public class ExamTypeController {

    private final ExamTypeService examTypeService;


    @Autowired
    public ExamTypeController(ExamTypeService examTypeService) {
        this.examTypeService = examTypeService;
    }



    @GetMapping("/all")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<ExamTypeResponse>> getAllExamTypes() {
        return ResponseEntity.ok(examTypeService.getAllExamType());
    }
}
