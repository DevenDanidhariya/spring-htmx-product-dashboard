package com.producthub.product_sync_app.product.controller;

import com.producthub.product_sync_app.product.dto.ProductDTO;
import com.producthub.product_sync_app.product.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Deven Danidhariya
 */
@Controller
@RequestMapping("/product")
public class ProductController {

  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/")
  public String index() {
    return "index";
  }


  @GetMapping("/all")
  public ResponseEntity getAllProducts() {
    List<ProductDTO> allProducts = productService.getAllProducts();

    return new ResponseEntity(allProducts, HttpStatus.OK);
  }

  @GetMapping("/products")
  public String getProducts(Model model) {
    // Add the list of products to the model
    model.addAttribute("products", productService.getAllProducts());
    // Return the fragment for the product table
    return "fragments/productTable :: productTable";
  }

}
