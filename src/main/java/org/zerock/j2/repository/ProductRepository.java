package org.zerock.j2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.j2.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
