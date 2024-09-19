package edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @EmbeddedId
    private OrderDetailId id;

    @MapsId("orderId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "order_id", nullable = false)
    private edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.models.Order order;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.models.Product product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Lob
    @Column(name = "note")
    private String note;

    public OrderDetailId getId() {
        return id;
    }

    public void setId(OrderDetailId id) {
        this.id = id;
    }

    public edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.models.Order getOrder() {
        return order;
    }

    public void setOrder(edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.models.Order order) {
        this.order = order;
    }

    public edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.models.Product getProduct() {
        return product;
    }

    public void setProduct(edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.models.Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}