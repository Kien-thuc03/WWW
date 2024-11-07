package edu.iuh.fit.th05.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductImageId implements Serializable {
    private static final long serialVersionUID = -2333555720728515033L;
    @NotNull
    @Column(name = "image_id", nullable = false)
    private Long imageId;

    @NotNull
    @Column(name = "product_id", nullable = false)
    private Long productId;

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductImageId entity = (ProductImageId) o;
        return Objects.equals(this.imageId, entity.imageId) &&
                Objects.equals(this.productId, entity.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId, productId);
    }

}