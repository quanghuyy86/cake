package vn.edu.hau.cake.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hau.cake.model.Product;
import vn.edu.hau.cake.model.ProductImages;
import vn.edu.hau.cake.repository.ProductImageRepository;
import vn.edu.hau.cake.repository.ProductRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceIplm implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads/";

    private boolean isEmptyUploadFile(MultipartFile image){
        return image == null || image.getOriginalFilename().isEmpty();
    }

    private boolean isEmptyUploadFile(MultipartFile[] image){
        if(image == null || image.length <= 0){
            return true;
        }
        if(image.length == 1 && image[0].getOriginalFilename().isEmpty()){
            return true;
        }
        return false;
    }

    private String getUniqueUploadFileName(String fileName) { //hàm dùng để nếu trùng tên ảnh thì gen ra tên ảnh khác biệt
        String[] splitFileName = fileName.split("\\.");
        return splitFileName[0] + System.currentTimeMillis() + "." + splitFileName[1];
    }
    @Override
    @Transactional
    public Product save(Product entity, MultipartFile avatar, MultipartFile[] picture) throws IOException {
        if(!isEmptyUploadFile(avatar)){
            Path filaeNameAndPath = Paths.get(UPLOAD_DIRECTORY, avatar.getOriginalFilename());
            Files.write(filaeNameAndPath, avatar.getBytes());
            entity.setAvatar("/uploads/" + avatar.getOriginalFilename());
        }
        if(!isEmptyUploadFile(picture)){
            for (MultipartFile pic : picture){
                Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, pic.getOriginalFilename());
                Files.write(fileNameAndPath, pic.getBytes());

                ProductImages productImages = new ProductImages();
                productImages.setPath("/uploads/" + pic.getOriginalFilename());
                productImages.setTitle(pic.getOriginalFilename());
//                productImageRepository.save(productImages);
                entity.addProductImages(productImages);
            }
        }
        return productRepository.save(entity);
    }
    @Override
    @Transactional
    public Product updateProduct(Integer id,Product product, MultipartFile avatar, MultipartFile[] picture) throws IOException{
        if(product != null){
            Optional<Product> product1 = productRepository.findById(id);
            if(product1 != null){
                product1.get().setTitle(product.getTitle());
                product1.get().setPrice(product.getPrice());
                product1.get().setPriceSale(product.getPriceSale());
                product1.get().setDetail(product.getDetail());
                product1.get().setShortDes(product.getShortDes());
                product1.get().setHot(product.getHot());
                product1.get().setCategories(product.getCategories());
                product1.get().setStatus(product.getStatus());

                if(!isEmptyUploadFile(avatar)){
                    new File(System.getProperty("user.dir") + product1.get().getAvatar()).delete();

                    String fileName = avatar.getOriginalFilename();
                    avatar.transferTo(new File(UPLOAD_DIRECTORY + fileName));
                    product1.get().setAvatar("/uploads/"+fileName);
                }

                if(!isEmptyUploadFile(avatar)){
                    if(product1.get().getProductImages() != null && product1.get().getProductImages().size() > 0){
                        for (ProductImages opi : product1.get().getProductImages()){
                            new File(System.getProperty("user.dir") + product1.get().getAvatar()).delete();
                        }
                    }
                    for (MultipartFile pic : picture){
                        String fileName = pic.getOriginalFilename();

                        pic.transferTo(new File(UPLOAD_DIRECTORY + fileName));

                        ProductImages pi = new ProductImages();
                        pi.setPath("/uploads/" + fileName);
                        pi.setTitle(fileName);
                        product.addProductImages(pi);
                    }
                }
            return productRepository.save(product1.get());

            }
        }
        return null;
    }

    @Override
    public List<Product> searchProduct(String keyword) {
        return productRepository.searchProduct(keyword);
    }

    @Override
    public Page<Product> gettAllProduct(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> searchPageProduct(String keyword, int page, int pageSize) {
        List list = this.searchProduct(keyword);

        Pageable pageable = PageRequest.of(page, pageSize);
        Integer start = (int)pageable.getOffset();
        Integer end = Math.toIntExact((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size() : pageable.getOffset() + pageable.getPageSize());

        list = list.subList(start, end);
        return new PageImpl<Product>(list, pageable, this.searchProduct(keyword).size());
    }

    @Override
    public List<Product> findByCategoryId1(){
        return productRepository.findProductsInCategory1();
    }

    @Override
    public List<Product> findByCategoryId2(){
        return productRepository.findProductsInCategory2();
    }

    @Override
    public List<Product> findByCategoryId3(){
        return productRepository.findProductsInCategory3();
    }


    @Override
    public List<Product> findByProductHot(){
        return productRepository.findProductsHot();
    }

    @Override
    public List<Product> saveAll(List<Product> entities) {
        return (List<Product>)productRepository.saveAll(entities);
    }

    @Override
    public Optional<Product> findById(Integer integer) {
        return productRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return productRepository.existsById(integer);
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Iterable<Product> findAllById(Iterable<Integer> integers) {
        return productRepository.findAllById(integers);
    }

    @Override
    public long count() {
        return productRepository.count();
    }

    @Override
    public void deleteById(Integer id) {
        Boolean exists = productRepository.existsById(id);
        if(exists){
            productRepository.deleteById(id);
        }
    }

    @Override
    public void delete(Product entity) {

        productRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        productRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAll(Iterable<? extends Product> entities) {
        productRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }
}
