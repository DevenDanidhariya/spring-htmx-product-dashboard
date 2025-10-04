package com.producthub.product_sync_app.variant.repository;

import com.producthub.product_sync_app.variant.model.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
}
