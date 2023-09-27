package vn.edu.hau.cake.service;

import org.springframework.data.domain.Page;
import vn.edu.hau.cake.model.Contact;
import vn.edu.hau.cake.model.Product;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    Contact save(Contact entity);

    List<Contact> searchContact(String keyword);

    Page<Contact> gettAllContact(int page, int pageSize);

    Page<Contact> searchPageContact(String keyword, int page, int pageSize);

    List<Contact> saveAll(List<Contact> entities);

    Optional<Contact> findById(Integer integer);

    boolean existsById(Integer integer);

    Iterable<Contact> findAll();

    Iterable<Contact> findAllById(Iterable<Integer> integers);

    long count();

    void deleteById(Integer integer);

    void delete(Contact entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAll(Iterable<? extends Contact> entities);

    void deleteAll();


}
