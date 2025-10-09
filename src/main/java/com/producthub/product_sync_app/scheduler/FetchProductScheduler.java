package com.producthub.product_sync_app.scheduler;

import com.fasterxml.jackson.databind.JsonNode;
import com.producthub.product_sync_app.product.model.Product;
import com.producthub.product_sync_app.product.repository.ProductRepository;
import com.producthub.product_sync_app.variant.model.Variant;
import com.producthub.product_sync_app.variant.repository.VariantRepository;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Deven Danidhariya
 * <p>
 * This class represents a scheduled task responsible for fetching up the products.
 * </p>
 * The scheduled task relies on an external API to fetch the actual products.
 */
@Component
public class FetchProductScheduler {


  private static final Logger LOGGER = LogManager.getLogger(FetchProductScheduler.class);

  private final ProductRepository productRepository;

  private final VariantRepository variantRepository;

  public FetchProductScheduler(ProductRepository productRepository,
      VariantRepository variantRepository) {
    this.productRepository = productRepository;
    this.variantRepository = variantRepository;
  }

  @Value("${api.url}")
  private String apiUrl;

  /**
   * @author Deven Danidhariya
   * <p>
   * This method uses the rest template for communicate and fetch prodict record as JSON and mapout
   * with product, and it's respecctiv variants.
   * <p>
   * The method is invoked automatically by Spring based, on the cron expression specified.
   */
  @Scheduled(cron = "0 0 0 * * *")
//  @Scheduled(initialDelay = 0, fixedDelay = Long.MAX_VALUE)
  public void fetchAndSaveProducts() {

    LOGGER.info(
        "Scheduling Job Started: Fetching product and variant details from external service.");

    RestTemplate restTemplate = new RestTemplate();
    JsonNode rootNode = restTemplate.getForObject(apiUrl, JsonNode.class);
    JsonNode products = rootNode.get("products");

    for (JsonNode productNode : products) {
      // Create product entity
      Product product = new Product();

      product.setId(productNode.get("id").asLong());
      product.setTitle(productNode.hasNonNull("title") ? productNode.get("title").asText() : null);
      product.setHandle(
          productNode.hasNonNull("handle") ? productNode.get("handle").asText() : null);
      product.setBodyHtml(
          productNode.hasNonNull("body_html") ? productNode.get("body_html").asText() : null);
      product.setPublishedAt(
          productNode.hasNonNull("published_at") ? productNode.get("published_at").asText() : null);
      product.setCreatedAt(
          productNode.hasNonNull("created_at") ? productNode.get("created_at").asText() : null);
      product.setUpdatedAt(
          productNode.hasNonNull("updated_at") ? productNode.get("updated_at").asText() : null);
      product.setVendor(
          productNode.hasNonNull("vendor") ? productNode.get("vendor").asText() : null);
      product.setProductType(
          productNode.hasNonNull("product_type") ? productNode.get("product_type").asText() : null);
      List<String> tags = new ArrayList<>();
      JsonNode tagsNode = productNode.get("tags");
      if (tagsNode != null && tagsNode.isArray()) {
        tagsNode.forEach(jsonNode -> tags.add(jsonNode.asText()));
      }
      product.setTags(String.join(",", tags));

      // Save product
      productRepository.save(product);

      // Save variants
      JsonNode variantsNode = productNode.get("variants");
      for (JsonNode variantNode : variantsNode) {
        Variant variant = new Variant();
        variant.setId(variantNode.get("id").asLong());
        variant.setProduct(product);
        variant.setTitle(variantNode.get("title").asText());

        variant.setOption1(
            variantNode.hasNonNull("option1") ? variantNode.get("option1").asText() : null);
        variant.setOption2(
            variantNode.hasNonNull("option2") ? variantNode.get("option2").asText() : null);
        variant.setOption3(
            variantNode.hasNonNull("option3") ? variantNode.get("option3").asText() : null);
        variant.setSku(variantNode.hasNonNull("sku") ? variantNode.get("sku").asText() : null);
        variant.setPrice(
            variantNode.hasNonNull("price") ? variantNode.get("price").asDouble() : 0.0);
        variant.setAvailable(
            variantNode.hasNonNull("available") && variantNode.get("available").asBoolean());

        JsonNode featuredImageNode = variantNode.get("featured_image");
        if (featuredImageNode != null && featuredImageNode.hasNonNull("src")) {
          variant.setFeaturedImageUrl(featuredImageNode.get("src").asText());
        } else {
          variant.setFeaturedImageUrl(null);
        }

        variant.setCreatedAt(
            variantNode.hasNonNull("created_at") ?
                variantNode.get("created_at").asText() : null);
        variant.setUpdatedAt(
            variantNode.hasNonNull("updated_at") ?
                variantNode.get("updated_at").asText() : null);

        //save variants
        variantRepository.save(variant);
      }
    }
    LOGGER.info(
        "Scheduling Job Completed: Product and variant details fetched and stored successfully.");
  }


}
