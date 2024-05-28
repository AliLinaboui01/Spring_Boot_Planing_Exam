package ensah.com.restapi_spring_project.services;

import ensah.com.restapi_spring_project.Dto.Responce.GroupDto;
import ensah.com.restapi_spring_project.Dto.Responce.ProfDto;
import ensah.com.restapi_spring_project.models.personnel.Group;
import ensah.com.restapi_spring_project.models.personnel.Prof;
import ensah.com.restapi_spring_project.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    //this function to remove not used attributes DTO
    public List<GroupDto> getAllGroupDtos() {
        List<Group> groups = groupRepository.findAll();
        return groups.stream()
                .map(this::mapGroupToGroupDto)
                .collect(Collectors.toList());
    }

    private GroupDto mapGroupToGroupDto(Group group) {
        GroupDto groupDto = new GroupDto();
        groupDto.setId(group.getId());
        groupDto.setGroup_name(group.getGroup_name());
        // Map Prof entities to ProfDto objects
        List<ProfDto> profDtos = group.getGroup_prof()
                .stream()
                .map(this::mapProfToProfDto)
                .collect(Collectors.toList());
        groupDto.setProfDtoList(profDtos);
        return groupDto;
    }

    private ProfDto mapProfToProfDto(Prof prof) {
        ProfDto profDto = new ProfDto();
        profDto.setId(prof.getId());
        profDto.setFirstName(prof.getUser().getFirstName());
        profDto.setLastName(prof.getUser().getLastName());
        profDto.setEmail(prof.getUser().getEmail());
        profDto.setDepartement_name(prof.getDepartment().getName());
        profDto.setField_name(prof.getField().getName());
        return profDto;
    }

    // create grp just with grp name
    public void save(Group group) {
        groupRepository.save(group);
    }




    // ceate group of profs
    public boolean createGroupWithProfs(Integer id, Group group) {
        try {
            Optional<Group> groupToUpdateOptional = groupRepository.findById(id);
            Group groupToUpdate = groupToUpdateOptional.orElseThrow(() -> new RuntimeException("Group Not Found"));

            groupToUpdate.setGroup_prof(group.getGroup_prof());
            groupRepository.save(groupToUpdate);

            return true;
        } catch (Exception e) {
            // Log the exception if necessary
            return false;
        }
}


}
