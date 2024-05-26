package ensah.com.restapi_spring_project.controllers;


import ensah.com.restapi_spring_project.models.Exam;
import ensah.com.restapi_spring_project.models.Group;
import ensah.com.restapi_spring_project.services.ExamService;
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
public class ExamController {
    private final ExamService examTypeService;
    @Autowired

    public ExamController(ExamService examTypeService) {
        this.examTypeService = examTypeService;
    }


    @GetMapping("/exams")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<Exam> getAllExams() {

        return examTypeService.getAllExams();
    }
}
