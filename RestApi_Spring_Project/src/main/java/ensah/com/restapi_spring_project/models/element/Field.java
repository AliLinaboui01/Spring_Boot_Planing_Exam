package ensah.com.restapi_spring_project.models.element;


import ensah.com.restapi_spring_project.models.personnel.Prof;
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
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "field" , fetch = FetchType.LAZY)
    private List<Prof> profs;
}
