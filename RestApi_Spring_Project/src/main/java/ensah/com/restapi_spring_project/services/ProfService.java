package ensah.com.restapi_spring_project.services;


import ensah.com.restapi_spring_project.Dto.Request.proof.CreateRequest;
import ensah.com.restapi_spring_project.Dto.Request.proof.UpdateRequest;
import ensah.com.restapi_spring_project.Dto.Responce.prof.ProfDto;
import ensah.com.restapi_spring_project.mappers.ProfessorMapper;
import ensah.com.restapi_spring_project.models.personnel.Monitoring;
import ensah.com.restapi_spring_project.models.personnel.Prof;
import ensah.com.restapi_spring_project.repositories.FiledRepository;
import ensah.com.restapi_spring_project.repositories.ProfRepository;
import ensah.com.restapi_spring_project.security.user.Role;
import ensah.com.restapi_spring_project.security.user.User;
import ensah.com.restapi_spring_project.security.user.UserRepository;
import ensah.com.restapi_spring_project.models.personnel.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfService {

    private final ProfRepository profRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final FiledRepository filedRepository;

    public List<ProfDto> getAllProfs() {
        return profRepository.findAll().stream()
                .map(ProfessorMapper::mapToProfDto)
                .collect(Collectors.toList());
    }


    public ProfDto createProfessor(CreateRequest createRequest){
        var user = User.builder()
                .firstName(createRequest.getFirstName())
                .lastName(createRequest.getLastName())
                .email(createRequest.getEmail())
                .password(passwordEncoder.encode(createRequest.getPassword()))
                .role(Role.PROFESSOR)
                .build();
        try  {
            userRepository.save(user);
            var field = filedRepository.findById(createRequest.getFieldId()).orElseThrow(() -> new IllegalArgumentException("field not found"));

            Prof prof = new Prof();
            prof.setField(field);
            prof.setDepartment(field.getDepartment());
            prof.setUser(user);
            profRepository.save(prof);

            return ProfessorMapper.mapToProfDto(prof);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Prof> getAllProfsforExam() {
        return profRepository.findAll();
    }

    public void save(Prof prof) {
        profRepository.save(prof);
    }
    public Optional<Prof> getProfById(Integer id) {
        return profRepository.findById(id);
    }


    @Transactional
    public ResponseEntity<String> deleteProf(Integer userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            Prof prof = profRepository.findByUser(user);
            if (prof == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Prof not found for user id: " + userId);
            }

            // Remove the prof from the associated group
            if (prof.getGroup() != null) {
                prof.getGroup().getGroup_prof().remove(prof);
                prof.setGroup(null);
            }

            // Nullify or remove relationships to avoid unintended deletions
            if (prof.getMonitorings() != null) {
                for (Monitoring monitoring : prof.getMonitorings()) {
                    monitoring.getProfs().remove(prof);
                }
            }

            if (prof.getProfCoordinatorMonitoring() != null) {
                for (Monitoring monitoring : prof.getProfCoordinatorMonitoring()) {
                    monitoring.setProfCoordinator(null);
                }
            }

            if (prof.getDepartment() != null) {
                prof.getDepartment().getProfs().remove(prof);
                prof.setDepartment(null);
            }

            if (prof.getField() != null) {
                prof.getField().getProfs().remove(prof);
                prof.setField(null);
            }

            profRepository.delete(prof);

            return ResponseEntity.status(HttpStatus.OK)
                    .body("Successfully deleted Prof with user id: " + userId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete Prof with user id: " + userId);
        }
    }


    public List<Prof> findProfessorsByIds(List<Integer> professorIds) {
        List<Prof> profs = new ArrayList<>();
        for (Integer id:professorIds ) {
            Prof prof = profRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Professor with ID " + id + " not found"));
            profs.add(prof);
        }
        return profs;
    }

    public Prof getByUser(User user){
        return profRepository.findByUser(user);
    }
}

