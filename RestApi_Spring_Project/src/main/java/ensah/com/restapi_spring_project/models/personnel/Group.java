package ensah.com.restapi_spring_project.models.personnel;

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
@Table(name = "_group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String group_name;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "group"
    )
    private List<Prof> group_prof;
}
