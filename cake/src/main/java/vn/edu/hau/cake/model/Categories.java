package vn.edu.hau.cake.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_category")
public class Categories extends BaseEntity{
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @Column(name = "seo", length = 1000, nullable = true)
    private String seo;
    @OneToMany(cascade = CascadeType.ALL, //Khi xóa 1 category thì tất cả pruducts sẽ bị xóa
            fetch = FetchType.LAZY, // khi lấy product đấy, thì nó sẽ k lấy tất cả các sản phẩm product đấy, chỉ lấy thông tin của mặt hàng đấy thôi
            mappedBy = "categories") //giá trị của mappedBy bằng tên property của ManyToOne
    private Set<Product> products = new HashSet<Product>();

    public Categories(String name, String description, String seo, Set<Product> products) {
        this.name = name;
        this.description = description;
        this.seo = seo;
        this.products = products;
    }
    public Categories() {

    }
    public Categories(Integer id, Boolean status, Integer createdBy, Integer updatedBy, Date createdDate, Date updatedDate, String name, String description, String seo, Set<Product> products) {
        super(id, status, createdBy, updatedBy, createdDate, updatedDate);
        this.name = name;
        this.description = description;
        this.seo = seo;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeo() {
        return seo;
    }

    public void setSeo(String seo) {
        this.seo = seo;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }


}
