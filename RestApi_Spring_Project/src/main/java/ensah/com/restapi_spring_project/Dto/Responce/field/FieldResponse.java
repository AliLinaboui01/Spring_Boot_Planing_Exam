package ensah.com.restapi_spring_project.Dto.Responce.field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldResponse {

    private Integer id;
    private String name;

}
