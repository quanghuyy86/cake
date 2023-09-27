package vn.edu.hau.cake.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hau.cake.model.Contact;
import vn.edu.hau.cake.model.Product;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {
    @Query("SELECT c FROM Contact c WHERE c.name like %?1%")
    List<Contact> searchContact(String keyword);

    Page<Contact> findAll(Pageable pageable);
}
