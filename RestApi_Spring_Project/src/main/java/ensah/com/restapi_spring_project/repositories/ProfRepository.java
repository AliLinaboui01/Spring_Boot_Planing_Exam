package ensah.com.restapi_spring_project.repositories;

import ensah.com.restapi_spring_project.models.personnel.Prof;
import ensah.com.restapi_spring_project.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface ProfRepository extends JpaRepository<Prof,Integer> {
    @Query("SELECT p FROM Prof p JOIN p.monitorings m WHERE m.exam.start_date < :endDate AND m.exam.end_date > :startDate")
    List<Prof> findOccupiedProfs(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


    public Prof findByUser(User user);

}
