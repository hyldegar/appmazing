package com.campusdual.appmazing.controller;

import com.campusdual.appmazing.api.IProductService;
import com.campusdual.appmazing.model.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController()
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE, RequestMethod.OPTIONS}, allowedHeaders = "*")
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;


    @GetMapping
    public String testController(){
        return "Products controller works¡";
    }
    @PostMapping
    public String testController(@RequestBody String name){
        return "Products controller works, " + name + "!";
    }
    @GetMapping(value ="/testMethod")
    public String testControllerMethod(){
        return "Products controller method works!";
    }
    @PostMapping(value = "/get")
    public ProductDTO queryProduct(@RequestBody ProductDTO productDTO){
        return productService.queryProduct(productDTO);
    }
    @GetMapping(value = "/getAll")
    public List<ProductDTO> queryAllProducts(){
        return productService.queryAllProducts();
    }
    @PostMapping(value = "/add")
    public int addProduct(@RequestBody ProductDTO productDTO){
        return productService.insertProduct(productDTO);
    }
    @PutMapping(value = "/update")
    public int updateProduct(@RequestBody ProductDTO productDTO){
        return productService.updateProduct(productDTO);
    }
    @PostMapping(value = "/delete")
    public int deleteProduct(@RequestBody ProductDTO productDTO){
        return productService.deleteProduct(productDTO);
    }

    @GetMapping(value = "/getActiveProducts")
    public List<ProductDTO> queryActiveProducts(){
        return productService.queryActiveProducts();
    }

    @GetMapping(value = "/getProductsPriceBiggerThan189")
    public  List<ProductDTO> queryPriceBiggerThan189(){
        return productService.queryPriceBiggerThan189();
    }
    /*@GetMapping(value = "/getProductsPriceBiggerThanNumber")
    public List<ProductDTO> queryPriceBiggerThanOther(BigDecimal price){
        return productService.queryPriceBiggerThanOther(price);
    }*/

    @PostMapping(value = "/getProductsPriceBiggerThanNumber")
    public List<ProductDTO> queryPriceBiggerThanOther(@RequestBody ProductDTO productDTO){

        BigDecimal price = productDTO.getPrice();
        return productService.queryPriceBiggerThanOther(price);
    }
    @PostMapping(value = "/getProductsPriceBiggerThanNumber2")
    List<ProductDTO> queryPriceBiggerThanProvided(@RequestBody ProductDTO productDTO){

        BigDecimal price = productDTO.getPrice();
        return productService.queryPriceBiggerThanProvided(price);
    }

}
