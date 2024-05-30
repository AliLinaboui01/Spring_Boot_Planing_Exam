package ensah.com.restapi_spring_project.repositories;

import ensah.com.restapi_spring_project.models.exam.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session,Integer> {
    Session findByTitle(String title);

}
