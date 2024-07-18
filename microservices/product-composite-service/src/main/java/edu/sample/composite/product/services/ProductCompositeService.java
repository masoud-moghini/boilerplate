package edu.sample.composite.product.services;

import edu.sample.api.Product;
import edu.sample.api.ProductAggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static java.util.logging.Level.FINE;

@RestController
public class ProductCompositeService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductCompositeService.class);
    private final ProductCompositeIntegration integration;

    public ProductCompositeService(ProductCompositeIntegration integration) {
        this.integration = integration;
    }


    @GetMapping(
            value = "/product-composite/{productId}",
            produces = "application/json")
    public Mono<ProductAggregate> getProduct(
            @PathVariable int productId,
            @RequestParam(value = "delay", required = false, defaultValue = "0") int delay,
            @RequestParam(value = "faultPercent", required = false, defaultValue = "0") int faultPercent
    ){
        LOG.info("Will get composite product info for product.id={}", productId);
        return Mono.zip(
                values -> createProductAggregate(
                        (Product) values[0]),
                integration.getProduct(productId, delay, faultPercent)
                .doOnError(ex -> LOG.warn("getCompositeProduct failed: {}", ex.toString()))
                .log(LOG.getName(), FINE));
    }
    private ProductAggregate createProductAggregate(Product product) {


        // 1. Setup product info
        int productId = product.getProductId();
        String name = product.getName();
        int weight = product.getWeight();

        // 2. Copy summary recommendation info, if available

        return new ProductAggregate(productId, name, weight);
    }
}
