package com.producthub.product_sync_app.variant.dto;

import com.producthub.product_sync_app.variant.model.Variant;

public record VariantDTO(
    Long id,
    Long productId,
    String title,
    String option1,
    String option2,
    String option3,
    String sku,
    Double price,
    Boolean available,
    String featuredImageUrl,
    String createdAt,
    String updatedAt

) {

  public static VariantDTO convertVariantToVariantDTO(Variant variant) {
    return new VariantDTO(variant.getId(), variant.getProduct().getId(),
        variant.getTitle(),
        variant.getOption1(), variant.getOption2(), variant.getOption3(), variant.getSku(),
        variant.getPrice(), variant.getAvailable(),
        variant.getFeaturedImageUrl(), variant.getCreatedAt(), variant.getUpdatedAt());
  }


}
