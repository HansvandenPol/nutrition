package nl.nutrition.service;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import nl.nutrition.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDaoService {
  @Autowired
  private ProductRepository productRepository;

  public List<Product> findAllContaining(String productName) {
    return productRepository.findByProductDescriptionNlContainingOrProductDescriptionEnContaining(productName, productName);
  }

  public Product findById(Long id) {
    return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }
}
