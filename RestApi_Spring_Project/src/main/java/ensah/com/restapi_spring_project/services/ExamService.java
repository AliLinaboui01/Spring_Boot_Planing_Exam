package ensah.com.restapi_spring_project.services;


import ensah.com.restapi_spring_project.models.exam.Exam;
import ensah.com.restapi_spring_project.models.personnel.Monitoring;
import ensah.com.restapi_spring_project.models.personnel.Prof;
import ensah.com.restapi_spring_project.repositories.ExamRepository;
import ensah.com.restapi_spring_project.repositories.MonitoringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamService {

    private final ExamRepository examRepository;
    private final MonitoringRepository monitoringRepository;

    @Autowired
    public ExamService(ExamRepository examRepository, MonitoringRepository monitoringRepository) {
        this.examRepository = examRepository;
        this.monitoringRepository = monitoringRepository;
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }



    @Transactional
    public boolean createExam(Exam exam) {
        if (isConflictWithExistingExams(exam)) {
            return false; // Conflict detected
        }

        // Check for conflicts with Monitoring entries
        if (isConflictWithMonitoring(exam.getListExamMonitoring())) {
            return false; // Conflict detected
        }

        examRepository.save(exam);
        return true;
    }

    private boolean isConflictWithExistingExams(Exam newExam) {
        List<Exam> overlappingExams = examRepository.findOverlappingExams(newExam.getStart_date(), newExam.getEnd_date());

        for (Exam exam : overlappingExams) {
            for (Monitoring newMonitoring : newExam.getListExamMonitoring()) {
                for (Monitoring existingMonitoring : exam.getListExamMonitoring()) {
                    if (isConflictWithMonitoring(newMonitoring, existingMonitoring)) {
                        return true; // Conflict detected
                    }
                }
            }
        }
        return false; // No conflict
    }

    private boolean isConflictWithMonitoring(List<Monitoring> newMonitorings) {
        for (int i = 0; i < newMonitorings.size(); i++) {
            for (int j = i + 1; j < newMonitorings.size(); j++) {
                Monitoring newMonitoring1 = newMonitorings.get(i);
                Monitoring newMonitoring2 = newMonitorings.get(j);
                if (isConflictWithMonitoring(newMonitoring1, newMonitoring2)) {
                    return true; // Conflict detected
                }
            }
        }
        return false; // No conflict
    }

    private boolean isConflictWithMonitoring(Monitoring newMonitoring, Monitoring existingMonitoring) {
        for (Prof newProf : newMonitoring.getProfs_supervised()) {
            for (Prof existingProf : existingMonitoring.getProfs_supervised()) {
                if (newProf.getId().equals(existingProf.getId())) {
                    return true; // Conflict detected
                }
            }
        }
        return false; // No conflict
    }


}
