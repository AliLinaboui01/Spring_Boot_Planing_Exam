package ensah.com.restapi_spring_project.security.auth;

import ensah.com.restapi_spring_project.Dto.Responce.user.UserResponse;
import ensah.com.restapi_spring_project.models.personnel.Admin;
import ensah.com.restapi_spring_project.models.personnel.Prof;
import ensah.com.restapi_spring_project.security.user.Role;
import ensah.com.restapi_spring_project.security.user.User;
import ensah.com.restapi_spring_project.services.ProfService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;
    private final ProfService profService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterDto request){
        return ResponseEntity.ok(service.register(request));
    }
    @GetMapping("/admins")
    public ResponseEntity<List<UserResponse>> getAllAdmins(){
        return ResponseEntity.ok(service.getAllAdmins());
    }
    @PutMapping("/user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody UpdateRequest updateRequest){
         service.update(id,updateRequest);
         return ResponseEntity.ok().body("update with success");
    }
    @GetMapping("/professors")
    public ResponseEntity<List<UserResponse>> getAll(){
        return ResponseEntity.ok(service.getAllProfs());
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationDto request){
        return ResponseEntity.ok(service.authenticate(request));
    }
    @PostMapping("/refresh-token")
    public void refresh(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.refreshToken(request,response);
    }




}
