package ensah.com.restapi_spring_project.controllers.exam;


import ensah.com.restapi_spring_project.models.exam.Exam;
import ensah.com.restapi_spring_project.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class ExamController {
    private final ExamService examService;
    @Autowired

    public ExamController(ExamService examService) {
        this.examService = examService;
    }


    @GetMapping("/exams")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<Exam> getAllExams() {
        return examService.getAllExams();
    }




    // create EXAM here we create info related to EXAM  and affect all other props
    @PostMapping("/exams")
    public ResponseEntity<String> createExam(@RequestBody Exam exam) {
        boolean success = examService.createExam(exam);
        if (success) {
            return ResponseEntity.ok("Exam created successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create exam.");
        }
    }


}
