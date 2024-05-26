package ensah.com.restapi_spring_project.repositories;

import ensah.com.restapi_spring_project.models.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level,Integer> {
}
