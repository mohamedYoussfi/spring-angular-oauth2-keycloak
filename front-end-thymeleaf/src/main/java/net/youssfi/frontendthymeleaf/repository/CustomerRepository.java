package net.youssfi.frontendthymeleaf.repository;
import net.youssfi.frontendthymeleaf.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
