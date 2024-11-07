package edu.iuh.fit.th05.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "product_images")
public class ProductImage {
    @EmbeddedId
    private ProductImageId id;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Size(max = 255)
    @Column(name = "path")
    private String path;

    @Size(max = 255)
    @Column(name = "alternative")
    private String alternative;

    public ProductImageId getId() {
        return id;
    }

    public void setId(ProductImageId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAlternative() {
        return alternative;
    }

    public void setAlternative(String alternative) {
        this.alternative = alternative;
    }

}