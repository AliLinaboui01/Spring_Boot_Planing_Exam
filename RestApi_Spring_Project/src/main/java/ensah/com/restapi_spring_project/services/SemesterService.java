package ensah.com.restapi_spring_project.services;


import ensah.com.restapi_spring_project.Dto.Responce.semester.SemesterResponse;
import ensah.com.restapi_spring_project.models.exam.Semester;
import ensah.com.restapi_spring_project.repositories.SemesterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SemesterService {

    private final SemesterRepository semesterRepository;

    public List<SemesterResponse> getAllSemester() {
        List<Semester>  semester = semesterRepository.findAll();
        return semester.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public SemesterResponse convertToDto(Semester semester){
        return SemesterResponse.builder()
                .name(semester.getTitle())
                .build();
    }

    public void save(Semester semester) {
        semesterRepository.save(semester);
    }
}
