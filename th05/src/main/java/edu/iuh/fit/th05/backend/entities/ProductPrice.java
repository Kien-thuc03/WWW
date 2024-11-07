package edu.iuh.fit.th05.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

@Entity
@Table(name = "product_prices")
public class ProductPrice {
    @Id
    @Column(name = "price_date_time", nullable = false)
    private Instant id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @NotNull
    @Column(name = "price", nullable = false)
    private Double price;

    @Size(max = 255)
    @Column(name = "note")
    private String note;

    public Instant getId() {
        return id;
    }

    public void setId(Instant id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}