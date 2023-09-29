package vn.edu.hau.cake.repository;

import org.springframework.data.repository.CrudRepository;
import vn.edu.hau.cake.model.SaleOrderProducts;

public interface SaleOderProductsRepository extends CrudRepository<SaleOrderProducts, Integer> {
}
