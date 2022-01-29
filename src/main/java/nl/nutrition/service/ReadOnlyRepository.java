package nl.nutrition.service;

import java.io.Serializable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

@NoRepositoryBean
public interface ReadOnlyRepository<T, ID> extends Repository<T, ID> {
  T findById(ID id);
  Iterable<T> findAll();
}
