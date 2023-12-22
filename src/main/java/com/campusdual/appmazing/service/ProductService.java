package com.campusdual.appmazing.service;

import com.campusdual.appmazing.api.IProductService;
import com.campusdual.appmazing.model.Product;
import com.campusdual.appmazing.model.dao.ProductDao;
import com.campusdual.appmazing.model.dto.ProductDTO;
import com.campusdual.appmazing.model.dto.dtomapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("ProductService")
@Lazy
public class ProductService implements IProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public ProductDTO queryProduct(ProductDTO productDTO) {

        Product product = ProductMapper.INSTANCE.toEntity(productDTO);/* Usamos el dto que recibimos por parámetro y
                                                                        con el método del DAO lo convertimos a entidad.*/
        /*
        product= productDao.getReferenceById(product.getId()); /* El método del DAO accede a la BBDD y devuelve el registro
                                                                que coincide con la información que le envía. Al final
                                                                devuelve el mismo registro que el dato que se le pasa
        productDTO= ProductMapper.INSTANCE.toDTO(product);/* Volvemos a usar el mapper para convertir la entidad en
                                                            un DTO y devolvemos el DTO.

        return productDTO;
        */

        return ProductMapper.INSTANCE.toDTO(productDao.getReferenceById(product.getId()));

    }
    @Override
    public List<ProductDTO> queryAllProducts() {
        /*
        @Override
        public List<ProductDTO> queryAllProducts() {
            List<Product> listaEntidadesProduct = this.productDao.findAll(); /* Primero invocamos el método findAll del repositorio
                                                                        y devuelve una lista de objeto de tipo Product que
                                                                        guardamos en una varialbe List.

            List<ProductDTO> listaDeEntidadesConvertidasADTO = ProductMapper.INSTANCE.toDTOList(listaEntidadesProduct);
            /* Usando la instancia del mappper (donde están las cabeceras implementadas (desarrolladas)) convertimos
             * la lista de entidades a una lista de POJOs

            return listaDeEntidadesConvertidasADTO;  /* Devolvemos la lista de DTOs (los POJOs)
        }*/
        return ProductMapper.INSTANCE.toDTOList(productDao.findAll());
    }
    @Override
    public int insertProduct(ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.toEntity(productDTO);
        productDao.saveAndFlush(product);
        return product.getId();
        /*
        @Override
        public int insertProduct(ProductDTO productDTO) {
            Product product = ProductMapper.INSTANCE.toEntity(productDTO); /* Recibimos un DTO por parámetro y con el mapper
                                                                      lo convertimos a entidad.

            this.productDao.saveAndFlush(product); /* Usamos el método "saveAndFlush" del DAO y así crea un nuevo registro en
                                              la BBDD con la información que contiene la entidad. Flush lo que hace es
                                              "renovar" la BBDD (F5).

            /* Por último se devuelve el número del id (la clave primaria de la tabla) del nuevo registro insertado en BBDD.
            return product.getId();
        }*/
    }

    @Override
    public int updateProduct(ProductDTO productDTO) {
        return insertProduct(productDTO);
    }

    @Override
    public int deleteProduct(ProductDTO productDTO) {
        int id = productDTO.getId();
        Product product = ProductMapper.INSTANCE.toEntity(productDTO);
        productDao.delete(product);
        return id;
    }

    @Override
    public List<ProductDTO> queryActiveProducts(){

        return ProductMapper.INSTANCE.toDTOList(productDao.findByActiveTrue());
    }
    @Override
    public List<ProductDTO> queryPriceBiggerThan189(){
        BigDecimal minimumPrice = new BigDecimal("1.89");
        return ProductMapper.INSTANCE.toDTOList(productDao.findByPriceGreaterThan(minimumPrice));

    }
    @Override
    public List<ProductDTO> queryPriceBiggerThanOther(BigDecimal price){
        return ProductMapper.INSTANCE.toDTOList(productDao.findByPriceGreaterThan(price));
    }

    @Override
    public List<ProductDTO> queryPriceBiggerThanProvided(BigDecimal price){
        return ProductMapper.INSTANCE.toDTOList(productDao.getProductsPriceBiggerThan(price));
    }





}
