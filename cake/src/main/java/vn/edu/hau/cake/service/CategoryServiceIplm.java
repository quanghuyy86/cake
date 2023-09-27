package vn.edu.hau.cake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hau.cake.model.Categories;
import vn.edu.hau.cake.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceIplm implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Categories save(Categories entity) {
        return categoryRepository.save(entity);
    }

    @Override
    public List<Categories> saveAll(List<Categories> entities) {
        return (List<Categories>)categoryRepository.saveAll(entities);
    }

    @Override
    public Optional<Categories> findById(Integer integer) {
        return categoryRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return categoryRepository.existsById(integer);
    }

    @Override
    public List<Categories> findAll() {
        return (List<Categories>) categoryRepository.findAll();
    }

    @Override
    public Iterable<Categories> findAllById(Iterable<Integer> integers) {
        return categoryRepository.findAllById(integers);
    }

    @Override
    public long count() {
        return categoryRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        categoryRepository.deleteById(integer);
    }

    @Override
    public void delete(Categories entity) {
        categoryRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        categoryRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAll(Iterable<? extends Categories> entities) {
        categoryRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
    }
}
