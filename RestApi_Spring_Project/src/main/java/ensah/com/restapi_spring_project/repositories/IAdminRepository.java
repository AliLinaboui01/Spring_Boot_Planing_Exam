package ensah.com.restapi_spring_project.repositories;

import ensah.com.restapi_spring_project.models.personnel.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepository extends JpaRepository<Admin,Integer> {
}
