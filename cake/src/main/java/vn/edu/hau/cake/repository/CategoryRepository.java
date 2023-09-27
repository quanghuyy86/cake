package vn.edu.hau.cake.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.repository.CrudRepository;
import vn.edu.hau.cake.model.Categories;

public interface CategoryRepository extends CrudRepository<Categories, Integer> {
}
