package com.mitocode.controller;

import com.mitocode.dto.CategoryDTO;
import com.mitocode.model.Category;
import com.mitocode.service.ICategoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
//@AllArgsConstructor
@RequiredArgsConstructor
public class CategoryController {

    //@Autowired
    private final ICategoryService service;
    @Qualifier("categoryMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> readAllCategories() throws Exception{
        List<CategoryDTO> list = service.readAll().stream().map(e-> mapper.map(e,CategoryDTO.class)).toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> readById(@PathVariable("id") Integer id) throws Exception{
        CategoryDTO categoria = mapper.map(service.readById(id),CategoryDTO.class);
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO dto) throws Exception{
        Category obj = service.save(mapper.map(dto,Category.class));
        return new ResponseEntity<>(mapper.map(obj, CategoryDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@RequestBody Category category, @PathVariable("id") Integer id) throws Exception{
        Category obj = service.update(category, id);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    /*
    @GetMapping
    public Category saveCategory(Category category){
        return service.validAndSave(new Category(1, "TV", "Television" , true));
    }

     */
}
