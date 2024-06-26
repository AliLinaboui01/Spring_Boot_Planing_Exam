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
public class CreateMonitoring {

    private Integer roomId;
    private List<Integer> profIds;
    private Integer coordinatorId;
    private Integer adminId;
    private Integer groupId;
    private Integer profNumber;
}
