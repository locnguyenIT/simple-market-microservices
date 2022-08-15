package com.ntloc.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ntloc.product.ProductConstant.PRODUCT_NOT_FOUND;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDTO> getAllProduct() {
        List<ProductEntity> listProduct = productRepository.findAll();
        return productMapper.toListDTO(listProduct);
    }

    public ProductDTO getProduct(Long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(() ->
                new IllegalStateException(PRODUCT_NOT_FOUND));
        return productMapper.toDTO(product);
    }
}
