package vn.edu.hau.cake.service;

import vn.edu.hau.cake.model.ProductImages;

import java.util.List;
import java.util.Optional;

public interface ProductImageService {
    ProductImages save(ProductImages entity);

    List<ProductImages> saveAll(List<ProductImages> entities);

    Optional<ProductImages> findById(Integer integer);

    boolean existsById(Integer integer);

    List<ProductImages> findAll();

    List<ProductImages> findAllById(List<Integer> integers);

    long count();

    void deleteById(Integer integer);

    void delete(ProductImages entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAll(Iterable<? extends ProductImages> entities);

    void deleteAll();
}
