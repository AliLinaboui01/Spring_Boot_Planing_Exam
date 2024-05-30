package ensah.com.restapi_spring_project.controllers.personnel;


import ensah.com.restapi_spring_project.Dto.Request.group.GroupDtoRequest;
import ensah.com.restapi_spring_project.Dto.Responce.group.GroupDto;
import ensah.com.restapi_spring_project.Dto.Responce.group.GroupResponse;
import ensah.com.restapi_spring_project.Dto.Responce.prof.ProfDto;
import ensah.com.restapi_spring_project.models.personnel.Group;
import ensah.com.restapi_spring_project.services.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    // this to get without dto
    @GetMapping("/all")
    public ResponseEntity<List<GroupResponse>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    // get with DTO
    @GetMapping("/prof_groupsDto")
    public List<GroupDto> getAllGroupsDto() {
        return groupService.getAllGroupDtos();
    }
    // this function just to create groups name
    @PostMapping("/create")
    public void createNewGroup(@RequestBody Group group ) {
         groupService.save(group);
    }
    // this function to affect profs to groups

    @PostMapping("/create/{groupId}")
    public ResponseEntity<?> createGroupOfProfs(@PathVariable("groupId") Integer groupId, @RequestBody GroupDtoRequest groupDto) {
        return groupService.createGroupWithProfs(groupId, groupDto);
    }

    @GetMapping("/profs/{id}")
    public ResponseEntity<List<ProfDto>> getAllProfsByGroupId(@PathVariable Integer id){
        return ResponseEntity.ok(groupService.getAllProfsByGroupId(id));
    }

}
