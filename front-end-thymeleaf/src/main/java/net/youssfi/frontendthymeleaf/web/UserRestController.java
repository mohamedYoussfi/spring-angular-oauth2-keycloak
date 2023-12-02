package net.youssfi.frontendthymeleaf.web;

import net.youssfi.frontendthymeleaf.entities.AppUser;
import net.youssfi.frontendthymeleaf.repository.AppUserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {
    private AppUserRepository appUserRepository;

    public UserRestController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }
    @GetMapping("/appUsers")
    public List<AppUser> appUsers(){
        return appUserRepository.findAll();
    }
}
