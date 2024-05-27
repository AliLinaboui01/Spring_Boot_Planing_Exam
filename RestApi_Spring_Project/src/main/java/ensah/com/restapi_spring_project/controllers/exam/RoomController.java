package ensah.com.restapi_spring_project.controllers.exam;


import ensah.com.restapi_spring_project.models.exam.Room;
import ensah.com.restapi_spring_project.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class RoomController
{
    private final RoomService roomService;

  @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }



    @GetMapping("/rooms")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<Room> getAllRooms() {

        return roomService.getAllRooms();
    }
}
