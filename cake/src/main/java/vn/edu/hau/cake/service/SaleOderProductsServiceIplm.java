package vn.edu.hau.cake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hau.cake.model.SaleOrder;
import vn.edu.hau.cake.model.SaleOrderProducts;
import vn.edu.hau.cake.repository.SaleOderProductsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SaleOderProductsServiceIplm implements SaleOderProductsService {
    @Autowired
    private SaleOderProductsRepository saleOderProductsRepository;

    @Override
    public SaleOrderProducts save(SaleOrderProducts entity) {
        return saleOderProductsRepository.save(entity);
    }

    @Override
    public List<SaleOrderProducts> saveAll(List<SaleOrderProducts> entities) {
        return (List<SaleOrderProducts>) saleOderProductsRepository.saveAll(entities);
    }

    @Override
    public Optional<SaleOrderProducts> findById(Integer integer) {
        return saleOderProductsRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return saleOderProductsRepository.existsById(integer);
    }

    @Override
    public List<SaleOrderProducts> findAll() {
        return (List<SaleOrderProducts>) saleOderProductsRepository.findAll();
    }

    @Override
    public Iterable<SaleOrderProducts> findAllById(Iterable<Integer> integers) {
        return saleOderProductsRepository.findAllById(integers);
    }

    @Override
    public long count() {
        return saleOderProductsRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        saleOderProductsRepository.deleteById(integer);
    }

    @Override
    public void delete(SaleOrderProducts entity) {
        saleOderProductsRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        saleOderProductsRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAll(Iterable<? extends SaleOrderProducts> entities) {
        saleOderProductsRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        saleOderProductsRepository.deleteAll();
    }
}
