package nl.nutrition.service;

import java.util.List;
import nl.nutrition.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
  List<Product> findByProductDescriptionNlContainingOrProductDescriptionEnContaining(String productNameNl, String productNameEn);
}
