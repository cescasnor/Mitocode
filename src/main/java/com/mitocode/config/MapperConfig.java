package com.mitocode.config;

import com.mitocode.dto.CategoryDTO;
import com.mitocode.dto.ProductDTO;
import com.mitocode.model.Category;
import com.mitocode.model.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.BeanProperty;

@Configuration
public class MapperConfig {

    @Bean("categoryMapper")
    public ModelMapper categoryMapper(){
        ModelMapper mapper = new ModelMapper();

        //Lectura
        TypeMap<Category, CategoryDTO> typmeMap1 = mapper.createTypeMap(Category.class,CategoryDTO.class);
        typmeMap1.addMapping(Category::getName, (dest,v) -> dest.setNameCategory((String) v));

        //Escritura
        TypeMap<CategoryDTO, Category> typmeMap2 = mapper.createTypeMap(CategoryDTO.class,Category.class);
        typmeMap2.addMapping(CategoryDTO::getNameCategory, (dest,v) -> dest.setName((String) v));

        return mapper;
    }

    @Bean("productMapper")
    public ModelMapper productMapper(){
        ModelMapper mapper = new ModelMapper();

        //Lectura
        TypeMap<Product, ProductDTO> typmeMap1 = mapper.createTypeMap(Product.class,ProductDTO.class);
        typmeMap1.addMapping(e -> e.getCategory().getIdCategory(), (dest,v) -> dest.setIdCategoria((Integer) v) );

        //Escritura
        TypeMap<ProductDTO, Product> typmeMap2 = mapper.createTypeMap(ProductDTO.class,Product.class);
        typmeMap2.addMapping(ProductDTO::getIdCategoria, (dest,v) -> dest.getCategory().setIdCategory((Integer) v) );

        return mapper;
    }

    @Bean("defaultMapper")
    public ModelMapper defaultMapper(){
        return new ModelMapper();
    }
}
