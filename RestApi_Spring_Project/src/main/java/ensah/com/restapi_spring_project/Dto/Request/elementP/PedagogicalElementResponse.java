package ensah.com.restapi_spring_project.Dto.Request.elementP;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedagogicalElementResponse {
    private Integer id;
    private String name;
    private  Integer IdType;
    private String nameType;
}
