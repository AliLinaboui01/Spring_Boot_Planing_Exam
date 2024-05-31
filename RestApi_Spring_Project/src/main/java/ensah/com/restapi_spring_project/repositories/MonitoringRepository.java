package ensah.com.restapi_spring_project.repositories;


import ensah.com.restapi_spring_project.models.exam.Room;
import ensah.com.restapi_spring_project.models.personnel.Monitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitoringRepository extends JpaRepository<Monitoring ,Integer> {

}
