package ensah.com.restapi_spring_project.models.exam;


import ensah.com.restapi_spring_project.models.personnel.Monitoring;
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
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String name;
    private int capacity;

    @OneToMany(mappedBy = "room" ,fetch = FetchType.LAZY)
    private List<Monitoring> monitoringListInRoom;
}
