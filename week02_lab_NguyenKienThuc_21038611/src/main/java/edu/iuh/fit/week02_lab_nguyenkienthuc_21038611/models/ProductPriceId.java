package edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.time.Instant;
import java.util.Objects;

@Embeddable
public class ProductPriceId implements java.io.Serializable {
    private static final long serialVersionUID = -3416865459967136442L;
    @NotNull
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @NotNull
    @Column(name = "price_date_time", nullable = false)
    private Instant priceDateTime;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Instant getPriceDateTime() {
        return priceDateTime;
    }

    public void setPriceDateTime(Instant priceDateTime) {
        this.priceDateTime = priceDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductPriceId entity = (ProductPriceId) o;
        return Objects.equals(this.priceDateTime, entity.priceDateTime) &&
                Objects.equals(this.productId, entity.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(priceDateTime, productId);
    }

}