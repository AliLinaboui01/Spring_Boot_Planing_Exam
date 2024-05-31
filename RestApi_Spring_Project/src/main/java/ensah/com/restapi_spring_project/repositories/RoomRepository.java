package ensah.com.restapi_spring_project.repositories;

import ensah.com.restapi_spring_project.models.exam.Room;
import ensah.com.restapi_spring_project.models.personnel.Prof;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {
    @Query("SELECT r FROM Room r JOIN r.monitoringListInRoom m WHERE " +
            "m.exam.start_date < :endDate AND m.exam.end_date > :startDate")
    List<Room> findOccupiedRooms(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    @Query("SELECT r FROM Room r WHERE r.id NOT IN (SELECT m.room.id FROM Monitoring m)")
    List<Room> findRoomsNotInMonitoring();
}
