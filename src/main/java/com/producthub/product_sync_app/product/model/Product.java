package com.producthub.product_sync_app.product.model;

import com.producthub.product_sync_app.variant.model.Variant;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

  @Id
  private Long id;

  private String title;

  private String handle;

  @Column(columnDefinition = "TEXT")
  private String bodyHtml;

  @Column(name = "published_at")
  private String publishedAt;

  @Column(name = "created_at")
  private String createdAt;

  @Column(name = "updated_at")
  private String updatedAt;

  private String vendor;

  private String productType;

  @Column(name = "tags")
  private String tags;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Variant> variants;
}
