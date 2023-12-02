package net.youssfi.inventooryservice.repository;

import net.youssfi.inventooryservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mohamedyoussfi
 **/
public interface ProductRepository extends JpaRepository<Product,String> {
}
