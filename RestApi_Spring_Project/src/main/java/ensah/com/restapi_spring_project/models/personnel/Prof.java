package ensah.com.restapi_spring_project.models.personnel;

import ensah.com.restapi_spring_project.models.element.Department;
import ensah.com.restapi_spring_project.models.element.Field;
import ensah.com.restapi_spring_project.security.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Prof {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = true)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = true)
    private Field field;

    @ManyToMany
    @JoinTable(
            name = "profs_monitoring",
            joinColumns = @JoinColumn(name = "prof_id"),
            inverseJoinColumns = @JoinColumn(name = "monitoring_id")
    )
    private List<Monitoring> profsMonitoring;

    @OneToMany(mappedBy = "profCoordinator", cascade = CascadeType.ALL)
    private List<Monitoring> profCoordinatorMonitoring;

}
