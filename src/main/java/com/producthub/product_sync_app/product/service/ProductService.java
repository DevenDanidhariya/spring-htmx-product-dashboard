package com.producthub.product_sync_app.product.service;

import com.producthub.product_sync_app.product.dto.ProductDTO;
import com.producthub.product_sync_app.product.repository.ProductRepository;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author Deven Danidhariya
 */
@Service
public class ProductService {

  private static final Logger LOGGER = LogManager.getLogger(ProductService.class);

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }


  /**
   * @return List of ProductDTO which fetched retrieved
   * <p>
   * Fetch all Product Records
   * @author Deven Danidhariya
   */
  public List<ProductDTO> getAllProducts() {
    LOGGER.info("Retrieving all Products.");
    return productRepository.findAll().stream().map(ProductDTO::convertProDuctToProductDTO)
        .toList();
  }


}
