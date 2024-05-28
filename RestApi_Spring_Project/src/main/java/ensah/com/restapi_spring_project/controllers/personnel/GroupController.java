package ensah.com.restapi_spring_project.controllers.personnel;


import ensah.com.restapi_spring_project.Dto.Responce.GroupDto;
import ensah.com.restapi_spring_project.models.personnel.Group;
import ensah.com.restapi_spring_project.models.personnel.Prof;
import ensah.com.restapi_spring_project.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    // this function just to create groups name
    @PostMapping("group/prof_groups")
    public void createNewGroup(@RequestBody Group group ) {
         groupService.save(group);
    }

    // this function to affect profs to groups

    @PostMapping("group/prof_groups/{groupId}")
    public ResponseEntity<?> createGoupOfProfs(@PathVariable Integer groupId, @RequestBody Group profs) {
        boolean success = groupService.createGroupWithProfs(groupId, profs);
        if (success) {
            return ResponseEntity.ok().body("Group created successfully with associated professors.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create group or associate professors profs allredy in a groups.");
        }
    }

}
