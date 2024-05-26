package ensah.com.restapi_spring_project.models;

import ensah.com.restapi_spring_project.security.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ManyToAny;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Prof extends User {


    @ManyToOne
    @JoinColumn(name="departement_id")
    private Departement departement;


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
