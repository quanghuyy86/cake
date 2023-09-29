package vn.edu.hau.cake.repository;

import org.springframework.data.repository.CrudRepository;
import vn.edu.hau.cake.model.SaleOrder;

public interface SaleOrderRepository extends CrudRepository<SaleOrder, Integer> {
}
