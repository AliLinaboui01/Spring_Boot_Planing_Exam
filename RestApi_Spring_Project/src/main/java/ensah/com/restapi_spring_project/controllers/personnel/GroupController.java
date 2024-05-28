package ensah.com.restapi_spring_project.controllers.personnel;


import ensah.com.restapi_spring_project.Dto.Responce.GroupDto;
import ensah.com.restapi_spring_project.models.personnel.Group;
import ensah.com.restapi_spring_project.models.personnel.Prof;
import ensah.com.restapi_spring_project.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/admin")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }



    // this to get without dto
    @GetMapping("group/prof_groups")
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    // get with DTO

    @GetMapping("group/prof_groupsdtos")
    public List<GroupDto> getAllGroupsDto() {
        return groupService.getAllGroupDtos();
    }

    @PostMapping("group/prof_groups")
    public void createNewGroup(@RequestBody Group group ) {
         groupService.save(group);
    }
}
