package vn.edu.hau.cake.service;

import jakarta.servlet.http.HttpServletRequest;
import vn.edu.hau.cake.model.SaleOrder;

import java.util.List;
import java.util.Optional;

public interface SaleOderService {
    SaleOrder save(SaleOrder entity);

    SaleOrder save(SaleOrder saleOrder, HttpServletRequest request);

    List<SaleOrder> saveAll(List<SaleOrder> entities);

    Optional<SaleOrder> findById(Integer integer);

    boolean existsById(Integer integer);

    Iterable<SaleOrder> findAll();

    Iterable<SaleOrder> findAllById(Iterable<Integer> integers);

    long count();

    void deleteById(Integer integer);

    void delete(SaleOrder entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAll(Iterable<? extends SaleOrder> entities);

    void deleteAll();
}
