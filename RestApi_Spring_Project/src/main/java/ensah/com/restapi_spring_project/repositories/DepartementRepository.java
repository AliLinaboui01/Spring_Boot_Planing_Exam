package ensah.com.restapi_spring_project.repositories;

import ensah.com.restapi_spring_project.models.element.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Department, Integer> {
}
