package ensah.com.restapi_spring_project.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Date end_date_exact;

    private String exam_test;

    private String pv;
    private String rapport;



    @OneToMany(mappedBy = "exam")
    private List<Monitoring> exam_monitorings;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @ManyToOne
    @JoinColumn(name = "type_exam_id")
    private ExamType examType;


    @ManyToOne
    @JoinColumn(name="semester_id")
    private Semester semester;

}
