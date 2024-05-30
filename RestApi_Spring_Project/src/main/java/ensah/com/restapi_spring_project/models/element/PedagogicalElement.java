package ensah.com.restapi_spring_project.models.element;

import ensah.com.restapi_spring_project.models.exam.Exam;
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
public class PedagogicalElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;


    @ManyToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "prof_cord_id" )
    private Prof prof_cord;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prof_ensg_id")
    private Prof prof_of_elem;

    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;

    @ManyToOne
    @JoinColumn(name = "element_type_id")
    private ElementType elementType;

    @OneToMany(mappedBy = "pedagogicalElement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exam> exams;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id")
    private Field field;;

}
