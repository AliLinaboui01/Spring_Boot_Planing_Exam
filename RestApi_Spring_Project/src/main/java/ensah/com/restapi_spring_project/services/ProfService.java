package ensah.com.restapi_spring_project.services;


import ensah.com.restapi_spring_project.Dto.Responce.ProfDto;
import ensah.com.restapi_spring_project.models.personnel.Prof;
import ensah.com.restapi_spring_project.repositories.ProfRepository;
import ensah.com.restapi_spring_project.security.user.Role;
import ensah.com.restapi_spring_project.security.user.User;
import ensah.com.restapi_spring_project.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfService {


    private final ProfRepository profRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public ProfService(ProfRepository profRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.profRepository = profRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    public List<ProfDto> getAllProfs() {
        return profRepository.findAll().stream()
                .map(this::mapToProfDto)
                .collect(Collectors.toList());
    }

    private ProfDto mapToProfDto(Prof prof) {

        return ProfDto.builder()
                .id(prof.getUser().getId())
                .firstName(prof.getUser().getFirstName())
                .lastName(prof.getUser().getLastName())
                .email(prof.getUser().getEmail())
                .departement_name(prof.getDepartment().getName())
                .field_name(prof.getField().getName())
                .build();
    }

    public void save(Prof prof) {
        profRepository.save(prof);
    }

    public Optional<Prof> getProfById(Integer id) {
        return profRepository.findById(id);
    }
    // this function is to create prof with all atribute for example to have form
    // that have firstName , LastName , email ,Password , ...
    // and that affect auto in user table with the Prof role
    public void createProf(Prof profDetails) {
        User user = new User();
        user.setFirstName(profDetails.getUser().getFirstName());
        user.setLastName(profDetails.getUser().getLastName());
        user.setEmail(profDetails.getUser().getEmail());
        user.setPassword(passwordEncoder.encode(profDetails.getUser().getPassword()));
        user.setRole(Role.PROFESSOR);

        // Save the User entity to the database
        userRepository.save(user);

        // Create a new Prof entity and associate it with the User entity
        Prof prof = new Prof();
        prof.setDepartment(profDetails.getDepartment());
        prof.setField(profDetails.getField());
        prof.setUser(user);

        // Save the Prof entity to the database
         profRepository.save(prof);


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

    public void deleteProf(Integer id) {
        profRepository.deleteById(id);
    }
}
