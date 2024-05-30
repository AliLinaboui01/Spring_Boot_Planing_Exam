package ensah.com.restapi_spring_project.services;


import ensah.com.restapi_spring_project.Dto.Request.proof.CreateRequest;
import ensah.com.restapi_spring_project.Dto.Responce.prof.ProfDto;
import ensah.com.restapi_spring_project.mappers.ProfessorMapper;
import ensah.com.restapi_spring_project.models.personnel.Prof;
import ensah.com.restapi_spring_project.repositories.FiledRepository;
import ensah.com.restapi_spring_project.repositories.ProfRepository;
import ensah.com.restapi_spring_project.security.user.Role;
import ensah.com.restapi_spring_project.security.user.User;
import ensah.com.restapi_spring_project.security.user.UserRepository;
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

    public Prof updateProf(Integer id, Prof profDetails) {
        Prof prof = profRepository.findById(id).orElseThrow(() -> new RuntimeException("Prof not found"));
        prof.setDepartment(profDetails.getDepartment());
        prof.setField(profDetails.getField());

        // Save the updated Prof entity
        User user = prof.getUser();
        // Update user fields if provided in profDetails
        if (profDetails.getUser() != null) {
            User userDetails = profDetails.getUser();
            System.out.println("info User" + userDetails.getFirstName());
            if (userDetails.getFirstName() != null) {
                user.setFirstName(userDetails.getFirstName());
            }
            if (userDetails.getLastName() != null) {
                user.setLastName(userDetails.getLastName());
            }
            if (userDetails.getEmail() != null) {
                user.setEmail(userDetails.getEmail());
            }
            if (userDetails.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            }
        }

        // Save the updated user entity
        userRepository.save(user);
        // Save the updated Prof entity
        return profRepository.save(prof);
    }
    @Transactional
    public ResponseEntity<String> deleteProf(Integer profId) {
        try {
            Optional<Prof> profOptional = profRepository.findById(profId);
            if (profOptional.isPresent()) {
                profRepository.delete(profOptional.get());
                return ResponseEntity.ok("Prof deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Prof with id: " + profId + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete Prof with id: " + profId);
        }
    }
    public List<Prof> findProfessorsByIds(List<Integer> professorIds) {


        List<Prof> profs = new ArrayList<>();
        for (Integer id:professorIds ) {
            Prof prof = profRepository.findById(id).get();
            profs.add(prof);
        }
        return profs;
    }
}

