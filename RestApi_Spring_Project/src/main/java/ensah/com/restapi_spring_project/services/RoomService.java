package ensah.com.restapi_spring_project.services;

import ensah.com.restapi_spring_project.Dto.Responce.room.RoomResponse;
import ensah.com.restapi_spring_project.Dto.Request.room.CreateRoomDto;
import ensah.com.restapi_spring_project.mappers.RoomMapper;
import ensah.com.restapi_spring_project.models.element.ElementType;
import ensah.com.restapi_spring_project.models.exam.Room;
import ensah.com.restapi_spring_project.repositories.ElementTypeRepository;
import ensah.com.restapi_spring_project.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final ElementTypeRepository elementTypeRepository;
    public List<RoomResponse> getAllRooms() {
       List<Room> rooms =  roomRepository.findAll();
       return rooms.stream().map(RoomMapper::mapToRoomResponse).collect(Collectors.toList());
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


    public List<Room> getAllRoomsNotInMonitoring() {
        return roomRepository.findRoomsNotInMonitoring();
    }

}
