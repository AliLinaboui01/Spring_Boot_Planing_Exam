package ensah.com.restapi_spring_project.services;

import ensah.com.restapi_spring_project.Dto.Responce.room.RoomResponse;
import ensah.com.restapi_spring_project.Dto.Request.room.CreateRoomDto;
import ensah.com.restapi_spring_project.models.exam.Room;
import ensah.com.restapi_spring_project.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

   @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    public List<Room> getAllRooms() {
       return roomRepository.findAll();
    }


    public RoomResponse createRoom(CreateRoomDto roomDto){

       var room = Room.builder()
               .name(roomDto.getName())
               .capacity(roomDto.getCapacity())
               .build();
        roomRepository.save(room);
        return RoomResponse.builder()
                .name(roomDto.getName())
                .capacity(roomDto.getCapacity())
                .build();
    }
}
