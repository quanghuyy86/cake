package vn.edu.hau.cake.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import vn.edu.hau.cake.model.Product;
import vn.edu.hau.cake.model.ProductImages;

import java.util.List;

public interface ProductImageRepository extends CrudRepository<ProductImages, Integer> {

}
