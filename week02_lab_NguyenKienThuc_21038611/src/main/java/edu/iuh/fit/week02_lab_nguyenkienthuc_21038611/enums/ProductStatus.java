package edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.enums;

public enum ProductStatus {
    TERMINATED(-1),
    ACTIVE(1),
    IN_ACTIVE(0);

    private final int status;

    ProductStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
