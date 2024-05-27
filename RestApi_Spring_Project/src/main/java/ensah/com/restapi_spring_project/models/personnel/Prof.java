package ensah.com.restapi_spring_project.models.personnel;

import ensah.com.restapi_spring_project.models.element.Department;
import ensah.com.restapi_spring_project.models.element.Field;
import ensah.com.restapi_spring_project.security.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Prof{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;


    @ManyToOne
    @JoinColumn(name = "filed_id")
    private Field field;


    @ManyToMany
    @JoinTable(name = "profs_monitoring",
            joinColumns = @JoinColumn(name = "monitoring_id"),
            inverseJoinColumns = @JoinColumn(name = "prof_id"))
    private List<Monitoring> profs_monitoring;

    @OneToMany(mappedBy = "prof_coordinator")
    private List<Monitoring> ProfCoordinatorMonitoring;



}
