package ensah.com.restapi_spring_project.Dto.Responce.room;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponse {
    private String name;
    private int capacity;
}
