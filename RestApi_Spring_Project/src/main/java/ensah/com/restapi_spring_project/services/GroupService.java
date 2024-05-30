package ensah.com.restapi_spring_project.services;

import ensah.com.restapi_spring_project.Dto.Request.group.GroupDtoRequest;
import ensah.com.restapi_spring_project.Dto.Responce.group.GroupDto;
import ensah.com.restapi_spring_project.Dto.Responce.group.GroupResponse;
import ensah.com.restapi_spring_project.Dto.Responce.prof.ProfDto;
import ensah.com.restapi_spring_project.mappers.ExamMapper;
import ensah.com.restapi_spring_project.mappers.GroupMapper;
import ensah.com.restapi_spring_project.mappers.ProfessorMapper;
import ensah.com.restapi_spring_project.models.personnel.Group;
import ensah.com.restapi_spring_project.models.personnel.Prof;
import ensah.com.restapi_spring_project.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupService {


    private final GroupRepository groupRepository ;
    private final ProfService profService;


    @Autowired
    public GroupService(GroupRepository groupRepository, ProfService profService) {
        this.groupRepository = groupRepository;
        this.profService = profService;
    }

    public List<GroupResponse> getAllGroups() {
        List<Group> groups =  groupRepository.findAll();
        return groups.stream().map(GroupMapper::mapToGroupResponse).collect(Collectors.toList());
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
    // create group of profs
    public ResponseEntity<String> createGroupWithProfs(Integer groupId, GroupDtoRequest groupDto) {

        System.out.println("grp id " + groupId  +" and ids"+ groupDto.getProfIds());
        try {
            Optional<Group> groupToUpdateOptional = groupRepository.findById(groupId);
            if (groupToUpdateOptional.isEmpty()) {
                throw new RuntimeException("Group Not Found");
            }
            Group groupToUpdate = groupToUpdateOptional.get();

            List<Prof> professors = profService.findProfessorsByIds(groupDto.getProfIds());

            if (professors.isEmpty()) {
                throw new RuntimeException("No professors found with the provided IDs.");
            }
            groupToUpdate.setGroup_prof(professors);
            groupRepository.save(groupToUpdate);

            return ResponseEntity.ok().body("Group updated successfully with associated professors.");
        } catch (Exception e) {
            // Log the exception if necessary
            e.printStackTrace(); // Add proper logging mechanism here
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update group or associate professors.");
        }
    }

    public List<ProfDto> getAllProfsByGroupId(Integer idGroup){
        Group group = groupRepository.findById(idGroup)
                .orElseThrow(() -> new IllegalArgumentException("group not found"));

        return group.getGroup_prof().stream()
                .map(ProfessorMapper::convertToDto)
                .collect(Collectors.toList());
    }
    public List<Prof> getAllProfsByGroupIdForExam(Integer idGroup){
        Group group = groupRepository.findById(idGroup)
                .orElseThrow(() -> new IllegalArgumentException("group not found"));

        return group.getGroup_prof();

    }





}
