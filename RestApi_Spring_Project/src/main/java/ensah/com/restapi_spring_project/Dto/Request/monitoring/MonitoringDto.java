package ensah.com.restapi_spring_project.Dto.Request.monitoring;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonitoringDto {

    private Integer roomId;
    private Integer groupId;
    private Integer profNumber;
}
