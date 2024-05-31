package ensah.com.restapi_spring_project.Dto.Responce.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamResponse {
    private Integer id;
    private Date startDate;
    private Date endDate;
    private int exactTime;
    private int defaultTime;
    private String year;
    private String examTitle;
    private String session;
}
