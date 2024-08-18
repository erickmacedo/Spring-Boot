package com.example.apirest.apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.apirest.apirest.models.Product;
import com.example.apirest.apirest.repositories.ProductRepository;
import com.example.apirest.apirest.utils.ResponseHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;



    //exibir uma coleção de produtos
    @GetMapping
    public List<Product> listar(){
        
        return productRepository.findAll();

    }

    //consultar um produto

    @GetMapping ("/{id}")
    public ResponseEntity<Object> obter(@PathVariable Integer id){
        Optional<Product> product = productRepository.findById(id);

        if (!product.isPresent()) {
           //return o status code 404 
           return ResponseHandler.generate("Produto não encontrado", HttpStatus.NOT_FOUND);
        }
        //retornar o produto

        return new ResponseEntity<Object>(product.get(), HttpStatus.OK);

        
        
    }

    
    //cadastrar um produto

    @PostMapping()  
    public ResponseEntity<Object> create(@RequestBody Product product){
        if (product.getName() == null) {
            return ResponseHandler.generate("Nome do produto é obrigatorio", HttpStatus.NOT_FOUND);
        }
        if (product.getPrice() == null) {
            return ResponseHandler.generate("preço do produto é obrigatorio", HttpStatus.NOT_FOUND);
        }
        Product newProduct = productRepository.save(product);
        return new ResponseEntity<Object>(newProduct, HttpStatus.CREATED);

    }
    //editar um produto
    //excluir um produto
    
}
