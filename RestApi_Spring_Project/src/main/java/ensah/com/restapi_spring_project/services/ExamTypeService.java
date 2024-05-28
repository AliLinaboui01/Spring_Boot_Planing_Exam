package ensah.com.restapi_spring_project.services;


import ensah.com.restapi_spring_project.models.exam.ExamType;
import ensah.com.restapi_spring_project.repositories.ExamTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamTypeService {

    private final ExamTypeRepository examTypeRepository;

    @Autowired
    public ExamTypeService(ExamTypeRepository examTypeRepository) {
        this.examTypeRepository = examTypeRepository;
    }


    public List<ExamType>  getAllExamType() {
        return examTypeRepository.findAll();
    }

    public void save(ExamType examType) {
        examTypeRepository.save(examType);
    }
}
