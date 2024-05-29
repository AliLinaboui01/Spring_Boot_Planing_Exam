package ensah.com.restapi_spring_project.models.personnel;


import ensah.com.restapi_spring_project.models.exam.Room;
import ensah.com.restapi_spring_project.models.exam.Exam;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Monitoring {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;


    @ManyToMany(cascade = CascadeType.ALL)
    private List<Prof> profs_supervised ;

   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "coordinateur_prf_id")
    private Prof profCoordinator;

   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name="admin-respo_id")
   private Admin adminMonitoring;
}
