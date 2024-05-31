package ensah.com.restapi_spring_project.Dto.Request.exam;

import ensah.com.restapi_spring_project.Dto.Request.monitoring.MonitoringDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateExamDto {
    private Date startDate;
    private String year;
    private List<MonitoringDto> monitoringList;
    private Integer sessionId;
    private Integer examTypeId;
    private Integer pedagogicalElementId;

}
