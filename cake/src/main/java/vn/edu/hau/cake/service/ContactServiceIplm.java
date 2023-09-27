package vn.edu.hau.cake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.hau.cake.model.Contact;
import vn.edu.hau.cake.repository.ContactRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceIplm implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact save(Contact entity) {
        return contactRepository.save(entity);
    }
    @Override
    public List<Contact> searchContact(String keyword){
        return contactRepository.searchContact(keyword);
    }
    @Override
    public Page<Contact> gettAllContact(int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize);
        return contactRepository.findAll(pageable);
    }

    @Override
    public Page<Contact> searchPageContact(String keyword, int page, int pageSize){
        List list = this.searchContact(keyword);
        Pageable pageable = PageRequest.of(page, pageSize);
        Integer start = (int)pageable.getOffset();
        Integer end = Math.toIntExact((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size() : pageable.getOffset() + pageable.getPageSize());

        list = list.subList(start, end);
        return new PageImpl<Contact>(list, pageable, this.searchContact(keyword).size());
    }


    @Override
    public List<Contact> saveAll(List<Contact> entities) {
        return (List<Contact>)contactRepository.saveAll(entities);
    }

    @Override
    public Optional<Contact> findById(Integer integer) {
        return contactRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return contactRepository.existsById(integer);
    }

    @Override
    public Iterable<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public Iterable<Contact> findAllById(Iterable<Integer> integers) {
        return contactRepository.findAllById(integers);
    }

    @Override
    public long count() {
        return contactRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        contactRepository.deleteById(integer);
    }

    @Override
    public void delete(Contact entity) {
        contactRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        contactRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAll(Iterable<? extends Contact> entities) {
        contactRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        contactRepository.deleteAll();
    }
}
