package ensah.com.restapi_spring_project.models.element;

import ensah.com.restapi_spring_project.models.personnel.Prof;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   //@NotNull(message = "you cant create departement withou name")
    private String name;


    @OneToMany(mappedBy = "department" ,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Prof> profs;
}
