package ensah.com.restapi_spring_project.controllers.exam;


import ensah.com.restapi_spring_project.Dto.Request.exam.UpdateExam;
import ensah.com.restapi_spring_project.Dto.Responce.exam.ExamResponse;
import ensah.com.restapi_spring_project.Dto.Responce.room.RoomResponse;
import ensah.com.restapi_spring_project.Dto.Request.room.CreateRoomDto;
import ensah.com.restapi_spring_project.models.exam.Room;
import ensah.com.restapi_spring_project.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController

@RequestMapping("/api/room")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class RoomController
{
    private final RoomService roomService;

    @GetMapping("/allRooms")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<RoomResponse>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }


    @PostMapping("/createRoom")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<RoomResponse> create(@RequestBody CreateRoomDto roomDto){
        return ResponseEntity.ok(roomService.createRoom(roomDto));
    }

    @GetMapping("/available")
    public ResponseEntity<List<Room>> getAvailableRooms() {
        List<Room> availableRooms = roomService.getAllRoomsNotInMonitoring();
        return ResponseEntity.ok(availableRooms);
    }


}
