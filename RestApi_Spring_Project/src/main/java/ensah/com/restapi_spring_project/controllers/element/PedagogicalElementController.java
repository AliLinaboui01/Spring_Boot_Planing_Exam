package ensah.com.restapi_spring_project.controllers.element;


import ensah.com.restapi_spring_project.Dto.Request.elementP.PedagogicalElementRequestDto;
import ensah.com.restapi_spring_project.Dto.Responce.pedagogicalElem.PedagogicalElementDto;
import ensah.com.restapi_spring_project.services.PedagogicalElementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/pedagogiceElement")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class PedagogicalElementController {

    private final PedagogicalElementService pedagogicalElementService;


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<PedagogicalElementDto> getAllPedagogicalElement() {
        return  pedagogicalElementService.getAllPedagogicalElements();
    }


    @PostMapping("/create")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<String> createElementPedagogical(@RequestBody PedagogicalElementRequestDto pedagogicalElement) {
         return pedagogicalElementService.create(pedagogicalElement);
    }



}
