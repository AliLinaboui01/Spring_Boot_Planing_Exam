package ensah.com.restapi_spring_project.controllers.personnel;


import ensah.com.restapi_spring_project.Dto.Request.group.GroupDtoRequest;
import ensah.com.restapi_spring_project.Dto.Responce.group.GroupDto;
import ensah.com.restapi_spring_project.Dto.Responce.group.GroupResponse;
import ensah.com.restapi_spring_project.Dto.Responce.prof.ProfDto;
import ensah.com.restapi_spring_project.models.personnel.Group;
import ensah.com.restapi_spring_project.services.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController

@RequestMapping("/api/group")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class GroupController {

    private final GroupService groupService;
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<GroupResponse>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    @GetMapping("/prof_groupsDto")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<GroupDto> getAllGroupsDto() {
        return groupService.getAllGroupDtos();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<?> createGroupOfProfs(@RequestBody GroupDtoRequest groupDto) {
        return groupService.createGroupWithProfs(groupDto);
    }

    @GetMapping("/profs/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<ProfDto>> getAllProfsByGroupId(@PathVariable Integer id){
        return ResponseEntity.ok(groupService.getAllProfsByGroupId(id));
    }

}
