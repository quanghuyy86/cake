package vn.edu.hau.cake.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import vn.edu.hau.cake.model.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Query("SELECT c FROM Product c WHERE c.title like %?1%")
    List<Product> searchProduct(String keyword);

    @Query("SELECT p FROM Product p WHERE p.categories.id = 1")
    List<Product> findProductsInCategory1();

    @Query("SELECT p FROM Product p WHERE p.categories.id = 2")
    List<Product> findProductsInCategory2();

    @Query("SELECT p FROM Product p WHERE p.categories.id = 3")
    List<Product> findProductsInCategory3();

    @Query("SELECT p FROM Product p WHERE p.isHot = true")
    List<Product> findProductsHot();


    Page<Product> findAll(Pageable pageable);


}
