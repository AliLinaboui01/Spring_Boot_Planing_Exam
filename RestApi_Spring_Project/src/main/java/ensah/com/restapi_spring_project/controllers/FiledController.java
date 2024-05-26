package ensah.com.restapi_spring_project.controllers;


import ensah.com.restapi_spring_project.Dto.Responce.FiledDto;
import ensah.com.restapi_spring_project.Dto.Responce.ProfDto;
import ensah.com.restapi_spring_project.models.Field;
import ensah.com.restapi_spring_project.services.FiledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class FiledController {

    private final FiledService filedService;

    @Autowired
    public FiledController(FiledService filedService) {
        this.filedService = filedService;
    }


    @GetMapping("/filed")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<FiledDto> getAllProfs() {

        return filedService.getAllFiled();
    }
}
