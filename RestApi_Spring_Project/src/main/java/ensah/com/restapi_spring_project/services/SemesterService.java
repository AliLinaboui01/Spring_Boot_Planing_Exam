package ensah.com.restapi_spring_project.services;


import ensah.com.restapi_spring_project.models.exam.Semester;
import ensah.com.restapi_spring_project.repositories.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterService {

    private final SemesterRepository semesterRepository;


    @Autowired
    public SemesterService(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }


    public List<Semester> getAllSemester() {
        return semesterRepository.findAll();
    }


    public void save(Semester semester) {
        semesterRepository.save(semester);
    }
}
