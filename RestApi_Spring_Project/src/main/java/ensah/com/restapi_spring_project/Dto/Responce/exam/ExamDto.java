package ensah.com.restapi_spring_project.Dto.Responce.exam;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamDto {
    private Integer id;
    private Date start_date;
    private Date end_date;
    private Date end_date_exact;
    private String exam_test;
    private String pv;
    private String rapport;
    private List<Integer> monitoringIds; // Assuming we return only IDs of Monitorings
    private Integer sessionId;
    private Integer examTypeId;
    private Integer semesterId;
}
