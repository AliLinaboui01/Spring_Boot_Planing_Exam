package ensah.com.restapi_spring_project.mappers;

import ensah.com.restapi_spring_project.Dto.Responce.room.RoomResponse;
import ensah.com.restapi_spring_project.models.exam.Room;

public class RoomMapper {
    public static RoomResponse mapToRoomResponse(Room room){
        return RoomResponse.builder()
                .name(room.getName())
                .capacity(room.getCapacity())
                .build();
    }
}
