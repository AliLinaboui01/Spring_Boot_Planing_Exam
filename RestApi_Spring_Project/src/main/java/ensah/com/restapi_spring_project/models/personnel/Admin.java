package ensah.com.restapi_spring_project.models.personnel;

import ensah.com.restapi_spring_project.security.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Admin  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "adminMonitoring")
    private List<Monitoring> responsableOfMonitoring;
}
