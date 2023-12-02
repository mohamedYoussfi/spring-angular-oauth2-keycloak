package net.youssfi.frontendthymeleaf.repository;

import net.youssfi.frontendthymeleaf.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
}
