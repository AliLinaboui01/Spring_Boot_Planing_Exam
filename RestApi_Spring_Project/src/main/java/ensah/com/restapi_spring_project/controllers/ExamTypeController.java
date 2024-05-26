package ensah.com.restapi_spring_project.controllers;

import ensah.com.restapi_spring_project.models.ExamType;
import ensah.com.restapi_spring_project.models.Room;
import ensah.com.restapi_spring_project.services.ExamTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class ExamTypeController {

    private final ExamTypeService examTypeService;


    @Autowired
    public ExamTypeController(ExamTypeService examTypeService) {
        this.examTypeService = examTypeService;
    }



    @GetMapping("/examTypes")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<ExamType> getAllExamTypes() {

        return examTypeService.getAllExamType();
    }
}
