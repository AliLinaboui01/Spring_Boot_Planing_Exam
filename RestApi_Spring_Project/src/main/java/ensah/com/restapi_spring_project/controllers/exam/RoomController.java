package ensah.com.restapi_spring_project.controllers.exam;


import ensah.com.restapi_spring_project.Dto.Responce.room.RoomResponse;
import ensah.com.restapi_spring_project.Dto.request.room.CreateRoomDto;
import ensah.com.restapi_spring_project.models.exam.Room;
import ensah.com.restapi_spring_project.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/admin/room")
@PreAuthorize("hasRole('ADMIN')")
public class RoomController
{
    private final RoomService roomService;

  @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }



    @GetMapping("/allRooms")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @PostMapping("/createRoom")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<RoomResponse> create(@RequestBody CreateRoomDto roomDto){
        return ResponseEntity.ok(roomService.createRoom(roomDto));
    }
}
