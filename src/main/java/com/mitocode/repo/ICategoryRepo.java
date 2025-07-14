package com.mitocode.repo;

import com.mitocode.model.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Objects;

public interface ICategoryRepo extends IGenericRepo<Category,Integer> {

    List<Category> findByName(String name);

    List<Category> findByNameLike(String name);
    // %word% -> findByNameContains
    // %word -> findByNameStarsWith
    // word% -> findByNameEndsWith

    List<Category> findByNameAndEnabled(String name, boolean enabled);

    //Otros
    //List<Category> findByEnabledTrue();
    //Category findOneByName(String name)
    //List<Category> findTop5ByName(String name)
    //List<Category> findByNameIs(String name)
    //List<Category> findByNameIsNot(String name)
    //List<Category> findByNameIsNull(String name)
    //List<Category> findByNameIsNotNull(String name)
    //List<Category> findByNameEqualsIgnoreCase(String name)
    //List<Category> findByIdCategoryLessThan(Integer id)
    //List<Category> findByIdCategoryBetween(Integer id1, Integer id2)
    //List<Category> findByNameOrderByDescription(String description)
    //List<Category> findByNameOrderByDescriptionAsc(String description)

    //JPQL Java Persistence Query Language
    @Query("FROM Category c WHERE c.name = :name AND c.description LIKE %:description%")
    List<Category> findByNameAndDescription(@Param("name") String name, @Param("description") String description);

    @Query("SELECT new Category(c.name, c.description) FROM Category c WHERE c.name = :name AND c.description LIKE %:description%")
    //@Query("SELECT new com.mitocode.model.Category(c.name, c.description) FROM Category c WHERE c.name = :name AND c.description LIKE %:description%")
    List<Category> findByNameAndDescription2(@Param("name") String name, @Param("description") String description);

    //SQL : NativeQuery -> Sentencia tal cual ejecutarias directamente a la BD
    @Query(value = "SELECT * FROM category c WHERE c.name = :name", nativeQuery = true)
    List<Category> getNameSQL(@Param("name") String name);

    @Modifying
    @Query(value = "UPDATE category SET name = :name", nativeQuery = true)
    Integer updateNameSQL(@Param("name") String name);
}
