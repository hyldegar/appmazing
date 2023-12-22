package com.campusdual.appmazing.controller;

import com.campusdual.appmazing.api.IProductService;
import com.campusdual.appmazing.model.Product;
import com.campusdual.appmazing.model.dto.ProductDTO;
import com.campusdual.appmazing.model.dto.dtomapper.ProductMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    IProductService iProductService;

    @InjectMocks
    ProductController productController;

    Product product1 = new Product();
    Product product2 = new Product();

    @BeforeEach
    void init(){

        this.product1.setId(1);
        this.product1.setName("Oranges");
        this.product1.setPrice(BigDecimal.valueOf(1.80));
        this.product1.setStock(5);
        this.product1.setActive(true);
        this.product1.setDate_added(new Date(22 / 22 / 2023));


        this.product2.setId(2);
        this.product2.setName("Bananas");
        this.product2.setPrice(BigDecimal.valueOf(5.80));
        this.product2.setStock(10);
        this.product2.setActive(false);
        this.product2.setDate_added(new Date(22 / 22 / 2023));
    }
    @Test
    void queryProductTest(){

        ProductDTO productDTO = ProductMapper.INSTANCE.toDTO(this.product1);
        ProductDTO expectedProductDTO = ProductMapper.INSTANCE.toDTO(this.product1);

        Mockito.when(iProductService.queryProduct(Mockito.any(ProductDTO.class))).thenReturn(expectedProductDTO);

        ProductDTO result = productController.queryProduct(productDTO);

        Assertions.assertEquals(expectedProductDTO, result);

    }
    @Test
    void queryAllProductsTest() {

        List<ProductDTO> expectedProductDTOList = new ArrayList<>();
        expectedProductDTOList.add(ProductMapper.INSTANCE.toDTO(this.product1));
        expectedProductDTOList.add(ProductMapper.INSTANCE.toDTO(this.product2));

        Mockito.when(iProductService.queryAllProducts()).thenReturn(expectedProductDTOList);

        List<ProductDTO> result = productController.queryAllProducts();

        Assertions.assertEquals(result, expectedProductDTOList);

    }
    @Test
    void addProductTest(){

        Mockito.when(iProductService.insertProduct(Mockito.any())).thenReturn(this.product1.getId());

        ProductDTO productDTO = ProductMapper.INSTANCE.toDTO(this.product1);
        int result = productController.addProduct(productDTO);

        Assertions.assertEquals(result, productDTO.getId());
    }
    @Test
    void updateProductTest(){

        Mockito.when(iProductService.updateProduct(Mockito.any())).thenReturn(this.product2.getId());

        ProductDTO productDTO = ProductMapper.INSTANCE.toDTO(this.product2);
        int result = productController.updateProduct(productDTO);

        Assertions.assertEquals(result, productDTO.getId());

    }
    @Test
    void deleteProductTest(){
        Mockito.when(iProductService.deleteProduct(Mockito.any())).thenReturn(this.product1.getId());

        ProductDTO productDTO = ProductMapper.INSTANCE.toDTO(this.product1);
        int result = productController.deleteProduct(productDTO);

        Assertions.assertEquals(result, productDTO.getId());
    }
    @Test
    void queryActiveProductsTest(){
        List<ProductDTO> expectedProductDTOList = new ArrayList<>();
        expectedProductDTOList.add(ProductMapper.INSTANCE.toDTO(this.product1));
        expectedProductDTOList.add(ProductMapper.INSTANCE.toDTO(this.product2));

        Mockito.when(iProductService.queryActiveProducts()).thenReturn(expectedProductDTOList);

        List<ProductDTO> resultProsductDTOList = productController.queryActiveProducts();

        Assertions.assertEquals(resultProsductDTOList, expectedProductDTOList);
    }
    @Test
    void queryPriceBiggerThan189Test(){
        List<ProductDTO> expectedProductDTOList = new ArrayList<>();
        expectedProductDTOList.add(ProductMapper.INSTANCE.toDTO(this.product1));
        expectedProductDTOList.add(ProductMapper.INSTANCE.toDTO(this.product2));

        Mockito.when(iProductService.queryPriceBiggerThan189()).thenReturn(expectedProductDTOList);

        List<ProductDTO> resultProsductDTOList = productController.queryPriceBiggerThan189();

        Assertions.assertEquals(resultProsductDTOList, expectedProductDTOList);
    }
    @Test
    void queryPriceBiggerThanOtherTest(){

        List<ProductDTO> expectedProductDTOList = new ArrayList<>();
        expectedProductDTOList.add(ProductMapper.INSTANCE.toDTO(this.product1));
        expectedProductDTOList.add(ProductMapper.INSTANCE.toDTO(this.product2));

        Mockito.when(iProductService.queryPriceBiggerThanOther(Mockito.any())).thenReturn(expectedProductDTOList);

        List<ProductDTO> resultProsductDTOList = productController.queryPriceBiggerThanOther(ProductMapper.INSTANCE.toDTO(this.product1));

        Assertions.assertEquals(resultProsductDTOList, expectedProductDTOList);
    }
    @Test
    void queryPriceBiggerThanProvidedTest(){
        List<ProductDTO> expectedProductDTOList = new ArrayList<>();
        expectedProductDTOList.add(ProductMapper.INSTANCE.toDTO(this.product1));
        expectedProductDTOList.add(ProductMapper.INSTANCE.toDTO(this.product2));

        Mockito.when(iProductService.queryPriceBiggerThanProvided(Mockito.any())).thenReturn(expectedProductDTOList);

        List<ProductDTO> resultProsductDTOList = productController.queryPriceBiggerThanProvided(ProductMapper.INSTANCE.toDTO(this.product1));

        Assertions.assertEquals(resultProsductDTOList, expectedProductDTOList);
    }
    @Test
    void testControllerTest(){

        String result = productController.testController();

        Assertions.assertEquals(result, "Products controller works¡");
    }
    @Test
    void testControllerMethodTest(){

        String result = productController.testControllerMethod();

        Assertions.assertEquals(result, "Products controller method works!");
    }
    @Test
    void testControllerWithParamTest(){

        String result = productController.testController("Diego");

        Assertions.assertEquals(result,"Products controller works, Diego!");
    }
}
