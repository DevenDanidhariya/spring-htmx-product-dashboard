package com.producthub.product_sync_app.product.dto;

import com.producthub.product_sync_app.product.model.Product;
import com.producthub.product_sync_app.variant.dto.VariantDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record ProductDTO(
    Long id,
    String title,
    String handle,
    String bodyHtml,
    String publishedAt,
    String createdAt,
    String updatedAt,
    String vendor,
    String productType,
    List<String> tags,
    List<VariantDTO> variants
) {


  public static ProductDTO convertProDuctToProductDTO(Product product) {
    return new ProductDTO(product.getId(), product.getTitle(), product.getHandle(),
        product.getBodyHtml(), product.getPublishedAt(), product.getCreatedAt(),
        product.getUpdatedAt(), product.getVendor(), product.getProductType(),
        strToList(product.getTags()),
        product.getVariants().stream().map(VariantDTO::convertVariantToVariantDTO).toList());
  }

  public static List<String> strToList(String csv) {
    if (csv == null || csv.trim().isEmpty()) {
      return new ArrayList<>();
    }
    return Arrays.stream(csv.split(","))
        .map(String::trim)
        .filter(s -> !s.isEmpty())
        .toList();
  }

}
