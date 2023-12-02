package net.youssfi.inventooryservice;

import net.youssfi.inventooryservice.entities.Product;
import net.youssfi.inventooryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class InventooryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventooryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository){
        return args -> {
          productRepository.save(Product.builder()
                  .id(UUID.randomUUID().toString())
                          .name("Computer")
                          .price(5400)
                          .quantity(12)
                  .build());
            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Printer")
                    .price(3200)
                    .quantity(33)
                    .build());
            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Smart Phone")
                    .price(12000)
                    .quantity(4)
                    .build());
        };
    }
}
