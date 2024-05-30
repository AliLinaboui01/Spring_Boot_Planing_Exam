package ensah.com.restapi_spring_project.Dto.Request.field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFieldDto {

    private String name;
    private Integer departmentID;

}
