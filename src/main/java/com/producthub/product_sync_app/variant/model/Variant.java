package com.producthub.product_sync_app.variant.model;

import com.producthub.product_sync_app.product.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "variants")
public class Variant {

  @Id
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private Product product;

  private String title;

  private String option1;

  private String option2;

  private String option3;

  private String sku;

  private Double price;

  private Boolean available;

  @Column(name = "featured_image_url", columnDefinition = "TEXT", nullable = true)
  private String featuredImageUrl;

  @Column(name = "created_at")
  private String createdAt;

  @Column(name = "updated_at")
  private String updatedAt;
}
