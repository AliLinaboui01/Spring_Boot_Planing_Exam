package ensah.com.restapi_spring_project.controllers;


import ensah.com.restapi_spring_project.models.Group;
import ensah.com.restapi_spring_project.models.Room;
import ensah.com.restapi_spring_project.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }


    @GetMapping("/prof_groups")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<Group> getAllGroups() {

        return groupService.getAllGroups();
    }
}
