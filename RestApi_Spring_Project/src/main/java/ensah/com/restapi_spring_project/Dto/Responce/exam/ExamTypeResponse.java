package ensah.com.restapi_spring_project.Dto.Responce.exam;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamTypeResponse {
    private  String name ;
}
