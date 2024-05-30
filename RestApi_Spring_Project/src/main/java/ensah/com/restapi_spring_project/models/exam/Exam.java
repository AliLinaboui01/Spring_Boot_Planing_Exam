package ensah.com.restapi_spring_project.models.exam;


import ensah.com.restapi_spring_project.models.element.PedagogicalElement;
import ensah.com.restapi_spring_project.models.personnel.Monitoring;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.time.Year;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date start_date;
    private Date end_date;
    private int exactTime;
    private int defaultTime;
    private Year year;
    private String exam_test;

    private String pv;
    private String rapport;

    @OneToMany(mappedBy = "exam" , cascade = CascadeType.ALL)
    private List<Monitoring> ListExamMonitoring;

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "session_id")
    private Session session;

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "type_exam_id")
    private ExamType examType;


    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name="semester_id")
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "pedagogical_elem_id")
    private PedagogicalElement pedagogicalElement;
}

