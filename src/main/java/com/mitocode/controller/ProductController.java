package com.mitocode.controller;

import com.mitocode.dto.ProductDTO;
import com.mitocode.model.Product;
import com.mitocode.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService service;
    @Qualifier("productMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> readAllProducts() throws Exception{
        List<ProductDTO> list = service.readAll().stream().map(e-> mapper.map(e,ProductDTO.class)).toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> readById(@PathVariable("id") Integer id) throws Exception{
        ProductDTO categoria = mapper.map(service.readById(id),ProductDTO.class);
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid  @RequestBody ProductDTO dto) throws Exception{
        Product obj = service.save(mapper.map(dto,Product.class));
        return new ResponseEntity<>(mapper.map(obj, ProductDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@Valid @RequestBody Product category, @PathVariable("id") Integer id) throws Exception{
        Product obj = service.update(category, id);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

}
