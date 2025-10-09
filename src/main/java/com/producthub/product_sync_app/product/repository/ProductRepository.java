package com.producthub.product_sync_app.product.repository;

import com.producthub.product_sync_app.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Deven Danidhariya
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}