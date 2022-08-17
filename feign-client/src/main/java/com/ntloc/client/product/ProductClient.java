package com.ntloc.client.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product", url = "${client.product.url}")
public interface ProductClient {

    @GetMapping(path = "/api/v1/product/{productId}")
    ProductResponse getProduct(@PathVariable("productId") Long productId);
}
