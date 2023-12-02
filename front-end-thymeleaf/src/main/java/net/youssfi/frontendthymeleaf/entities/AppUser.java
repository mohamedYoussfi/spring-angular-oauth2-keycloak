package net.youssfi.frontendthymeleaf.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class AppUser {
    @Id
    private String userId;
    private String identityProvider;
    private String email;
    private String login;
    private String preferredUserName;
}
