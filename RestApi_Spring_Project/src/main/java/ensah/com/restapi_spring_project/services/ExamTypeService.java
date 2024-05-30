package ensah.com.restapi_spring_project.services;


import ensah.com.restapi_spring_project.Dto.Responce.exam.ExamTypeResponse;
import ensah.com.restapi_spring_project.mappers.ExamTypeMapper;
import ensah.com.restapi_spring_project.models.exam.ExamType;
import ensah.com.restapi_spring_project.repositories.ExamTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamTypeService {

    private final ExamTypeRepository examTypeRepository;

    public List<ExamTypeResponse>  getAllExamType() {
        List<ExamType> examType =  examTypeRepository.findAll();
        return examType.stream().map(ExamTypeMapper::mapToExamResponse).collect(Collectors.toList());
    }


    public void save(ExamType examType) {
        examTypeRepository.save(examType);
    }
}
