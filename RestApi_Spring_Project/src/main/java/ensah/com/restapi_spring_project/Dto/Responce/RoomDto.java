package ensah.com.restapi_spring_project.Dto.Responce;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {

    private Integer id;
    private String name;
    private int capacity;

}
