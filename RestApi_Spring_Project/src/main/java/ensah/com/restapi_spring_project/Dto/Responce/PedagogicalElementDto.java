package ensah.com.restapi_spring_project.Dto.Responce;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedagogicalElementDto {

    private Integer id;
    private String title;

    private ProfDto prof_coordinateur;

    private ProfDto prof_ensg;



 }
