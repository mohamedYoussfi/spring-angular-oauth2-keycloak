package net.youssfi.frontendthymeleaf.web;

import net.youssfi.frontendthymeleaf.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;


    @GetMapping("/securedMessage")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    public Map<String, String> securedEndpoint(){
        return Map.of("securedMessage","Secured Customer");
    }
    @GetMapping("/auth")
    @ResponseBody
    public Authentication authentication(Authentication authentication){
        return authentication;
    }
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/customOauth2login")
    public String oauth2Login(Model model){
        String authorizationRequestBaseUri = "oauth2/authorization";
        Map<String, String> oauth2AuthenticationUrls = new HashMap();
        Iterable<ClientRegistration> clientRegistrations = null;
        ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository)
                .as(Iterable.class);
        if (type != ResolvableType.NONE &&
                ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
            clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
            System.out.println("..............******");
        }

        clientRegistrations.forEach(registration ->{
                    oauth2AuthenticationUrls.put(registration.getClientName(),
                            authorizationRequestBaseUri + "/" + registration.getRegistrationId());
            System.out.println(registration.getClientName());
                });

        model.addAttribute("urls", oauth2AuthenticationUrls);
        return "oauth2LoginPage";
    }
    @GetMapping("/customers")
    public String customers(Model model){
        model.addAttribute("customers", customerRepository.findAll());
        return "customers";
    }
}
