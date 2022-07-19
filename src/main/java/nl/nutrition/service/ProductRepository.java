package nl.nutrition.service;

import java.util.List;
import nl.nutrition.model.dao.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
  List<Product> findByProductDescriptionNlContainingOrProductDescriptionEnContaining(
      String productNameNl, String productNameEn);
}
