package nl.nutrition.controller.product;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import nl.nutrition.model.Product;
import nl.nutrition.service.ProductDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("api/public/product")
@Validated
@Slf4j
public class ProductController {
  @Autowired private ProductDaoService productDaoService;
  private WebClient webClient;

  @GetMapping("search")
  public ResponseEntity<List<Product>> findProducts(@Size(max = 20, min = 2) @Pattern(regexp = "^[A-Za-z\\s]*$") @RequestParam(name = "product") String searchValue) {
    log.info("teste");
    webClient = WebClient.builder().build();

    return ResponseEntity.ok(productDaoService.findAllContaining(searchValue));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> findProduct(@Min(1) @Max(100000) @PathVariable Long id) {
    return ResponseEntity.ok(productDaoService.findById(id));
  }
}
