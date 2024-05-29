package ensah.com.restapi_spring_project.models.exam;

import ensah.com.restapi_spring_project.models.exam.Exam;
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
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;


    @OneToMany(mappedBy = "semester" ,  cascade = CascadeType.ALL)
    private List<Exam> examList;
}
