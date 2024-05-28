package ensah.com.restapi_spring_project.services;


import ensah.com.restapi_spring_project.models.exam.Exam;
import ensah.com.restapi_spring_project.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamService {

    private final ExamRepository examRepository;

    @Autowired
    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }


    public void save(Exam exam) {

        //
    }


    @Transactional
    public boolean createExam(Exam exam) {
        try {
            examRepository.save(exam);
            return true;
        } catch (Exception e) {
            // Log the exception if necessary
            return false;
        }
    }
}
