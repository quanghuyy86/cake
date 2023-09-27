package vn.edu.hau.cake.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "tbl_contact")
public class Contact extends BaseEntity{
    @Column(name = "full_name", length = 45, nullable = false)
    private String name;
    @Column(name = "email", length = 45, nullable = true)
    private String email;
    @Column(name = "sdt", length = 45, nullable = false)
    private String sdt;
    @Column(name = "message", length = 1000, nullable = false)
    private String message;

    public Contact(Integer id, Boolean status, Integer createdBy, Integer updatedBy, Date createdDate, Date updatedDate, String name, String email, String sdt, String message) {
        super(id, status, createdBy, updatedBy, createdDate, updatedDate);
        this.name = name;
        this.email = email;
        this.sdt = sdt;
        this.message = message;
    }

    public Contact() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
