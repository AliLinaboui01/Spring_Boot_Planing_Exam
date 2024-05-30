package ensah.com.restapi_spring_project.controllers.exam;


import ensah.com.restapi_spring_project.Dto.Request.exam.CreateExamDto;
import ensah.com.restapi_spring_project.Dto.Responce.exam.ExamResponse;
import ensah.com.restapi_spring_project.models.exam.Exam;
import ensah.com.restapi_spring_project.repositories.AdminRepository;
import ensah.com.restapi_spring_project.services.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController

@RequestMapping("/api/exam")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;
    private final AdminRepository adminRepository;



    @GetMapping("/exams")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<ExamResponse>> getAllExams() {
        return ResponseEntity.ok(examService.getAllExams());
    }


    // create EXAM here we create info related to EXAM  and affect all other props
    @PostMapping("/create")
    public ResponseEntity<ExamResponse> createExam (@RequestBody CreateExamDto createExamDto) {
        try {
            var createdExam = examService.createExam(createExamDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExam);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
