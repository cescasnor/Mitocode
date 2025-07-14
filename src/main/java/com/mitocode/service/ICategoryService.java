package com.mitocode.service;

import com.mitocode.model.Category;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoryService extends ICRUD<Category,Integer> {


    /*
    Category save(Category category) throws Exception;
    Category update(Category category, Integer id) throws Exception;
    List<Category> readAll() throws Exception;
    Category readById(Integer id) throws Exception;
    void deleteById(Integer id) throws Exception;
     */

    List<Category> findByName(String name);
    List<Category> findByNameLike(String name);
    List<Category> findByNameAndEnabled(String name, boolean enabled);
    List<Category> findByNameAndDescription(String name, String description);
}
