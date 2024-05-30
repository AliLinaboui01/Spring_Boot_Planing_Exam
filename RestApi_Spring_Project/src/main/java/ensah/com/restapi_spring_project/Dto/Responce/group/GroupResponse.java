package ensah.com.restapi_spring_project.Dto.Responce.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponse {

    private Integer id;
    private String name;
}
