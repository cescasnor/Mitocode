package com.mitocode.controller;

import com.mitocode.dto.CategoryDTO;
import com.mitocode.model.Category;
import com.mitocode.service.ICategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO dto) throws Exception{
        Category obj = service.save(mapper.map(dto,Category.class));
        return new ResponseEntity<>(mapper.map(obj, CategoryDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@Valid @RequestBody Category category, @PathVariable("id") Integer id) throws Exception{
        Category obj = service.update(category, id);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    //  QUERIES
    @GetMapping("/find/name/{name}")
    public ResponseEntity<List<CategoryDTO>> findByName(@PathVariable("name") String name) throws Exception{
        List<CategoryDTO> categoria = service.findByName(name).stream().map( e -> mapper.map(e, CategoryDTO.class) ).toList();
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @GetMapping("/find/name/like/{name}")
    public ResponseEntity<List<CategoryDTO>> findByNameLike(@PathVariable("name") String name) throws Exception{
        List<CategoryDTO> categoria = service.findByNameLike(name).stream().map( e -> mapper.map(e, CategoryDTO.class) ).toList();
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @GetMapping("/find/name/{name}/{enabled}")
    public ResponseEntity<List<CategoryDTO>> findByNameLikeAndEnabled(@PathVariable("name") String name, @PathVariable("enabled") Boolean enabled) throws Exception{
        List<CategoryDTO> categoria = service.findByNameAndEnabled(name,enabled).stream().map( e -> mapper.map(e, CategoryDTO.class) ).toList();
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @GetMapping("/find/name/description")
    public ResponseEntity<List<CategoryDTO>> getNameDescription(@RequestParam("name") String name, @RequestParam("description") String description) throws Exception{
        List<CategoryDTO> categoria = service.findByNameAndDescription(name,description).stream().map( e -> mapper.map(e, CategoryDTO.class) ).toList();
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    /*
    @GetMapping
    public Category saveCategory(Category category){
        return service.validAndSave(new Category(1, "TV", "Television" , true));
    }

     */
}
