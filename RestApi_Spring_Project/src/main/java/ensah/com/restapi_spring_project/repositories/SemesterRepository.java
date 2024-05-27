package ensah.com.restapi_spring_project.repositories;

import ensah.com.restapi_spring_project.models.exam.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SemesterRepository extends JpaRepository<Semester,Integer> {
}
