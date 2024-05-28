package ensah.com.restapi_spring_project.repositories;

import ensah.com.restapi_spring_project.models.exam.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Integer> {

    @Query("SELECT e FROM Exam e WHERE e.start_date < :end_date AND e.end_date > :start_date")
    List<Exam> findOverlappingExams(@Param("start_date") Date startDate, @Param("end_date") Date endDate);
}
