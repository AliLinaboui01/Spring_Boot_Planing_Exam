package ensah.com.restapi_spring_project.Dto.Responce.field;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FiledDto {
    private Integer id;

    private String filed_name;
}
