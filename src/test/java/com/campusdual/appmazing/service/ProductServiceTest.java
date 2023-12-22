package com.campusdual.appmazing.service;

import com.campusdual.appmazing.model.Contact;
import com.campusdual.appmazing.model.Product;
import com.campusdual.appmazing.model.dao.ProductDao;
import com.campusdual.appmazing.model.dto.ContactDTO;
import com.campusdual.appmazing.model.dto.ProductDTO;
import com.campusdual.appmazing.model.dto.dtomapper.ContactMapper;
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
public class ProductServiceTest {

    @Mock
    ProductDao productDao;

    @InjectMocks
    ProductService productService;

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

        /*Simulamos la llamada a la base de datos a través del DAO, y le decimos el valor que debería devolver en el
        return*/
        Mockito.when(this.productDao.getReferenceById(Mockito.anyInt())).thenReturn(this.product1);

        //Declaramos unas variables de tipo ProductDTO para hacer el código un poco mas sencillo de leer
        ProductDTO productDTOExpected = ProductMapper.INSTANCE.toDTO(this.product1);
        ProductDTO productDTOEvaluate = new ProductDTO();

        productDTOEvaluate.setId(1);

        //Hacemos las comprobaciones con el Assertions
        Assertions.assertNotNull(productDTOExpected);
        Assertions.assertEquals(productDTOEvaluate.getId(), this.productService.queryProduct(productDTOExpected).getId());

        //Verificamos con Mockito las veces que llamamos al método getReferenceById
        Mockito.verify(this.productDao, Mockito.times(1)).getReferenceById(Mockito.anyInt());
    }
    @Test
    void queryAllProductsTest(){

        List<Product> productList = new ArrayList<>();
        productList.add(this.product1);
        productList.add(this.product2);

        Mockito.when(this.productDao.findAll()).thenReturn(productList);

        List<ProductDTO> productDTOListExpected = ProductMapper.INSTANCE.toDTOList(productList);

        Assertions.assertEquals(2,this.productService.queryAllProducts().size());

        org.assertj.core.api.Assertions.assertThat(productDTOListExpected).usingRecursiveComparison()
                .isEqualTo(this.productService.queryAllProducts());

        Mockito.verify(this.productDao, Mockito.times(2)).findAll();
    }

    @Test
    void insertProductTest(){

        Mockito.when(this.productDao.saveAndFlush(Mockito.any(Product.class))).thenReturn(this.product1);

        ProductDTO productDTO = ProductMapper.INSTANCE.toDTO(this.product1);


        int productIdFromInsertedProduct = this.productService.insertProduct(productDTO);

        Assertions.assertNotNull(productIdFromInsertedProduct);
        Assertions.assertEquals(1,productIdFromInsertedProduct);
    }

    @Test
    void updateProductTest(){

        ProductDTO productDTOFromEntity = ProductMapper.INSTANCE.toDTO(product1);

        ProductDTO productDTOFOrUpdate = productDTOFromEntity;
        productDTOFOrUpdate.setName("No");
        productDTOFOrUpdate.setStock(100);
        productDTOFOrUpdate.setActive(false);

        Mockito.when(this.productDao.saveAndFlush(Mockito.any(Product.class))).thenReturn(ProductMapper.INSTANCE
                .toEntity(productDTOFOrUpdate));

        Assertions.assertEquals(1, productDTOFromEntity.getId());

        Assertions.assertNotNull(this.productService.updateProduct(productDTOFOrUpdate));
    }

    @Test
    void deleteProductTest(){


        ProductDTO productDTO = ProductMapper.INSTANCE.toDTO(this.product1);

        //Mockito.when(productDao.delete(Mockito.any(Product.class)));

        int result = productService.deleteProduct(productDTO);

        Mockito.verify(productDao, Mockito.times(1)).delete(Mockito.any(Product.class));

        Assertions.assertEquals(productDTO.getId(), result);
    }
    @Test
    void queryActiveProductsTest(){
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        //productList.add(product2);

        Mockito.when(this.productDao.findByActiveTrue()).thenReturn(productList);

        List<ProductDTO> productDTOList = ProductMapper.INSTANCE.toDTOList(productList);

        List<ProductDTO> productDTOResult = productService.queryActiveProducts();

        Assertions.assertEquals(1,productService.queryActiveProducts().size());

        org.assertj.core.api.Assertions.assertThat(productDTOList).usingRecursiveComparison()
                .isEqualTo(productDTOResult);

    }

    @Test
    void queryPriceBiggerThan189Test(){

        List<Product> productList2 = new ArrayList<>();
        //productList2.add(product1);
        productList2.add(product2);
        //BigDecimal minimunPrice = new BigDecimal("1.89");


        Mockito.when(this.productDao.findByPriceGreaterThan(Mockito.any(BigDecimal.class))).thenReturn(productList2);

        //List<ProductDTO> productDTOList = ProductMapper.INSTANCE.toDTOList(productList2);

        Assertions.assertEquals(1,productService.queryPriceBiggerThan189().size());


    }
    @Test
    void queryPriceBiggerThanOtherTest(){
        List<Product> productList2 = new ArrayList<>();
        productList2.add(product1);
        productList2.add(product2);
        BigDecimal minimunPrice = new BigDecimal("1.20");


        Mockito.when(this.productDao.findByPriceGreaterThan(Mockito.any(BigDecimal.class))).thenReturn(productList2);

        //List<ProductDTO> productDTOList = ProductMapper.INSTANCE.toDTOList(productList2);

        Assertions.assertEquals(2,productService.queryPriceBiggerThanOther(minimunPrice).size());
    }
    @Test
    void queryPriceBiggerThanProvidedTest(){
        List<Product> productList2 = new ArrayList<>();
        //productList2.add(product1);
        productList2.add(product2);
        BigDecimal minimunPrice = new BigDecimal("1.89");


        Mockito.when(this.productDao.getProductsPriceBiggerThan(Mockito.any(BigDecimal.class))).thenReturn(productList2);

        //List<ProductDTO> productDTOList = ProductMapper.INSTANCE.toDTOList(productList2);

        Assertions.assertEquals(1,productService.queryPriceBiggerThanProvided(minimunPrice).size());

    }


}
