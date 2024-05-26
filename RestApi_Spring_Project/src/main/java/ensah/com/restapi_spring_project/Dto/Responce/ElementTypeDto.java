package ensah.com.restapi_spring_project.Dto.Responce;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ElementTypeDto {
    private Integer id;
    private String type_name;
}
