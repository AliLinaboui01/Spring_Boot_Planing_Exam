package ensah.com.restapi_spring_project.models.personnel;

import ensah.com.restapi_spring_project.security.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Admin extends User {

    @OneToMany(mappedBy = "adminMonitoring")
    private List<Monitoring> responsableOfMonitoring;
}
