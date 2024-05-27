package ensah.com.restapi_spring_project.repositories;

import ensah.com.restapi_spring_project.models.exam.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Integer> {
}
