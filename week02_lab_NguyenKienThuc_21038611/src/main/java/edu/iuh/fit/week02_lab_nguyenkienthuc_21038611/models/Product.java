package edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.models;

import edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.enums.ProductStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "product")
@NamedQueries({
        @NamedQuery(name = "Product.findAll", query = "select p from Product p"),
        @NamedQuery(name = "Product.findById", query = "select p from Product p where p.id = :id"),
        @NamedQuery(name = "Product.findByStatus", query = "select p from Product p where p.status = :status"),
        @NamedQuery(name = "Product.deleteById", query = "delete from Product p where p.id = :id"),
        @NamedQuery(name = "Product.updateNameAndDescriptionAndUnitAndManufacturerNameAndStatusById", query = "update Product p set p.name = :name, p.description = :description, p.unit = :unit, p.manufacturerName = :manufacturerName, p.status = :status where p.id = :id")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Size(max = 50)
    @Column(name = "unit", length = 50)
    private String unit;

    @Size(max = 255)
    @Column(name = "manufacturer_name")
    private String manufacturerName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProductStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }



}