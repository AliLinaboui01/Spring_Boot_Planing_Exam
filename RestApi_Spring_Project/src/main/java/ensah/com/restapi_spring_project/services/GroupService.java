package ensah.com.restapi_spring_project.services;

import ensah.com.restapi_spring_project.models.personnel.Group;
import ensah.com.restapi_spring_project.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {


    private final GroupRepository groupRepository ;


    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getAllGroups() {

        return groupRepository.findAll();
    }

    public void save(Group group) {
        groupRepository.save(group);
    }
}
