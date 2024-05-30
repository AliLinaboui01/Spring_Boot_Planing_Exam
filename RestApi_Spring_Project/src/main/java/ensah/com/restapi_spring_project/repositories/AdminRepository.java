package ensah.com.restapi_spring_project.repositories;

import ensah.com.restapi_spring_project.models.personnel.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    @Query("SELECT a FROM Admin a JOIN a.responsableOfMonitoring m WHERE " +
            "m.exam.start_date < :endDate AND m.exam.end_date > :startDate")
    List<Admin> findOccupiedAdmins(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
