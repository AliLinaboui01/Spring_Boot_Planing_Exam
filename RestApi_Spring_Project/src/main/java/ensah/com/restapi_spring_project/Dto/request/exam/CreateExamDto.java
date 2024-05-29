package ensah.com.restapi_spring_project.Dto.Request.exam;

import ensah.com.restapi_spring_project.models.personnel.Monitoring;
import org.springframework.web.multipart.MultipartFile;

import java.time.Year;
import java.util.Date;
import java.util.List;

public class CreateExamDto {

    private Date startDate;
    private Date endDate;
    private int exactTime;
    private int defaultTime;
    private String year;
    private MultipartFile exam_test;
    private MultipartFile pv;
    private String rapport;

    private List<Integer> examMonitoringIds;
    private Integer semesterId;
    private Integer sessionId;
    private Integer examTypeId;
    private Integer pedagogicalElementId;

}
