package ensah.com.restapi_spring_project.models;

import ensah.com.restapi_spring_project.security.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Admin extends User {

    @OneToMany(mappedBy = "adminMonitoring")
    private List<Monitoring> responsableOfMonitoring;
}
