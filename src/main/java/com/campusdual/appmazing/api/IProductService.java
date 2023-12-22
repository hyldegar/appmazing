package com.campusdual.appmazing.api;

import com.campusdual.appmazing.model.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

public interface IProductService {

    //CRUD Operations
    ProductDTO queryProduct(ProductDTO productDTO);
    List<ProductDTO> queryAllProducts();
    int insertProduct(ProductDTO productDTO);
    int updateProduct(ProductDTO productDTO);
    int deleteProduct(ProductDTO productDTO);
    List<ProductDTO> queryActiveProducts();

    List<ProductDTO> queryPriceBiggerThan189();
    List<ProductDTO> queryPriceBiggerThanOther(BigDecimal price);

    List<ProductDTO> queryPriceBiggerThanProvided(BigDecimal price);
}
