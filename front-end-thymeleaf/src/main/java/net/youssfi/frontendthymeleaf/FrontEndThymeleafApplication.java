package net.youssfi.frontendthymeleaf;

import net.youssfi.frontendthymeleaf.entities.Customer;
import net.youssfi.frontendthymeleaf.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class FrontEndThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontEndThymeleafApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
        return args -> {
            customerRepository.save(Customer.builder().name("Mohamed").email("med@gmail.com").build());
            customerRepository.save(Customer.builder().name("Hanne").email("hanane@gmail.com").build());
            customerRepository.save(Customer.builder().name("Ines").email("ines@gmail.com").build());
            customerRepository.save(Customer.builder().name("Malak").email("malak@gmail.com").build());
        };
    }

}
