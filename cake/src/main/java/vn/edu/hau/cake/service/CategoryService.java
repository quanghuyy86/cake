package vn.edu.hau.cake.service;

import vn.edu.hau.cake.model.Categories;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Categories save(Categories entity);

    List<Categories> saveAll(List<Categories> entities);

    Optional<Categories> findById(Integer integer);

    boolean existsById(Integer integer);

    List<Categories> findAll();

    Iterable<Categories> findAllById(Iterable<Integer> integers);

    long count();

    void deleteById(Integer integer);

    void delete(Categories entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAll(Iterable<? extends Categories> entities);

    void deleteAll();
}
