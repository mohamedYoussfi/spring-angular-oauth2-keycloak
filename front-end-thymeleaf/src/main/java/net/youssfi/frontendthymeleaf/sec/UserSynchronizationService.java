package net.youssfi.frontendthymeleaf.sec;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;

import net.youssfi.frontendthymeleaf.entities.AppUser;
import net.youssfi.frontendthymeleaf.repository.AppUserRepository;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author mohamedyoussfi
 **/
@Service
@Slf4j
public class UserSynchronizationService {

    private AppUserRepository appUserRepository;
    private ClientRegistrationRepository clientRegistrationRepository;
    private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;


    public UserSynchronizationService(AppUserRepository appUserRepository, ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        this.appUserRepository = appUserRepository;
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    private void syncWithDatabase(String userId, Map<String, Object> attributes) throws JsonProcessingException {
        AppUser user = appUserRepository.findById(userId).orElse(null);
        if (user == null) {
            log.info("adding new user after successful login: {}", userId);
            user = new AppUser();
            user.setUserId(userId);
        } else {
            log.info("updating existing user after successful login: {}", userId);
        }
        user.setLogin(attributes.getOrDefault("login","").toString());
        user.setEmail(String.valueOf(attributes.getOrDefault("email","")));
        user.setPreferredUserName(attributes.getOrDefault("preferred_username","").toString());
        appUserRepository.save(user);
    }

    @EventListener(AuthenticationSuccessEvent.class)
    public void onAuthenticationSuccessEvent(final AuthenticationSuccessEvent event) throws JsonProcessingException {
        if(event.getAuthentication().getAuthorities() instanceof OidcUser oidcUser){
            syncWithDatabase(oidcUser.getSubject(),oidcUser.getAttributes());
        } else if(event.getAuthentication().getPrincipal() instanceof OAuth2User oauth2user) {
            syncWithDatabase(oauth2user.getName(), oauth2user.getAttributes());
        }
        System.out.println(".............");

    }

}