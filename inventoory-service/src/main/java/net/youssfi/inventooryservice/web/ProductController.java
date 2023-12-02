package net.youssfi.inventooryservice.web;

import net.youssfi.inventooryservice.entities.Product;
import net.youssfi.inventooryservice.repository.ProductRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mohamedyoussfi
 **/
@RestController
@RequestMapping("/api")
public class ProductController {
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @GetMapping("/products")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }
    @GetMapping("/products/{id}")
    public Product findProductById(@PathVariable String id){
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }
    @GetMapping("/auth")
    public Authentication authentication(Authentication authentication){
        return authentication;
    }
}
