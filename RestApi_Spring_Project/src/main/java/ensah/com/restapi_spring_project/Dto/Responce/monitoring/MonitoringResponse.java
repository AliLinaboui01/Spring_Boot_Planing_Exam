package ensah.com.restapi_spring_project.Dto.Responce.monitoring;

import ensah.com.restapi_spring_project.Dto.Responce.prof.ProfDto;
import ensah.com.restapi_spring_project.models.personnel.Prof;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonitoringResponse {

    private List<ProfDto> profs;
    private String coordinator;
    private String admin;
}
