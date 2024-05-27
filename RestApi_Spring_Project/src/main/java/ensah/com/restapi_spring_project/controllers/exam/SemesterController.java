package ensah.com.restapi_spring_project.controllers.exam;


import ensah.com.restapi_spring_project.models.exam.Semester;
import ensah.com.restapi_spring_project.services.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class SemesterController {

    private final SemesterService semesterService;


    @Autowired
    public SemesterController(SemesterService semesterService) {
        this.semesterService = semesterService;
    }



    @GetMapping("/semesters")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<Semester> getAllSemesters() {
        return semesterService.getAllSemester();
    }
}
