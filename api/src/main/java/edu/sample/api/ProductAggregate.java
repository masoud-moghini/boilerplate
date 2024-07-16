package edu.sample.api;

public class ProductAggregate {
    private final int productId;
    private final String name;
    private final int weight;

    public ProductAggregate() {
        productId = 0;
        name = null;
        weight = 0;
    }

    public ProductAggregate(
            int productId,
            String name,
            int weight) {

        this.productId = productId;
        this.name = name;
        this.weight = weight;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

}
