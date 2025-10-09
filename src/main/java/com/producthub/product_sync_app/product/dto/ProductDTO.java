package com.producthub.product_sync_app.product.dto;

import com.producthub.product_sync_app.product.model.Product;
import com.producthub.product_sync_app.variant.dto.VariantDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Deven Danidhariya
 */
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


  /**
   * @param product The Product entity containing the data to be mapped.
   * @return A ProductDTO object populated with the mapped data from the Product entity.
   * <p>
   * Maps a Product entity to a ProductDTO.
   * <p>
   * This static method takes a Product object as input, extracts its relevant data, and returns a
   * ProductDTO that contains the mapped values. The ProductDTO is used to represent the product
   * data in a more suitable format for transfer or display.
   * <p>
   * @author Deven Danidhariya
   */
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
