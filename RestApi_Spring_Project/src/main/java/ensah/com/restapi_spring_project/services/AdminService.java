package ensah.com.restapi_spring_project.services;


import ensah.com.restapi_spring_project.models.personnel.Admin;
import ensah.com.restapi_spring_project.repositories.AdminRepository;
import ensah.com.restapi_spring_project.security.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    public void save(Admin admin) {
        adminRepository.save(admin);
    }

    public List<Admin> getAll(){
        return adminRepository.findAll();
    }

    public Admin getByUser(User user){
        return adminRepository.getByUser(user);
    }
}
