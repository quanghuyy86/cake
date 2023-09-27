package vn.edu.hau.cake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hau.cake.model.ProductImages;
import vn.edu.hau.cake.repository.ProductImageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImageServiceIplm implements ProductImageService{
    @Autowired
    private ProductImageRepository productImageRepository;

    @Override
    public ProductImages save(ProductImages entity) {
        return productImageRepository.save(entity);
    }

    @Override
    public List<ProductImages> saveAll(List<ProductImages> entities) {
        return (List<ProductImages>)productImageRepository.saveAll(entities);
    }

    @Override
    public Optional<ProductImages> findById(Integer integer) {
        return productImageRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return productImageRepository.existsById(integer);
    }

    @Override
    public List<ProductImages> findAll() {
        return (List<ProductImages>)productImageRepository.findAll();
    }

    @Override
    public List<ProductImages> findAllById(List<Integer> integers) {
        return (List<ProductImages>)productImageRepository.findAllById(integers);
    }

    @Override
    public long count() {
        return productImageRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        productImageRepository.deleteById(integer);
    }

    @Override
    public void delete(ProductImages entity) {
        productImageRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        productImageRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAll(Iterable<? extends ProductImages> entities) {
        productImageRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        productImageRepository.deleteAll();
    }
}
