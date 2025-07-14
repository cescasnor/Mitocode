package com.mitocode.service.Impl;

import com.mitocode.model.Category;
import com.mitocode.model.Client;
import com.mitocode.repo.ICategoryRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.ICategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@AllArgsConstructor
@RequiredArgsConstructor
public class CategoryServiceImpl extends CRUDImpl<Category, Integer> implements ICategoryService {

    //@Autowired
    private final ICategoryRepo categoryRepo;

    @Override
    protected IGenericRepo<Category, Integer> getRepo() {
        return categoryRepo;
    }

    @Override
    public List<Category> findByName(String name) {
        return categoryRepo.findByName(name);
    }

    @Override
    public List<Category> findByNameLike(String name) {
        return categoryRepo.findByNameLike("%" + name + "%");
    }

    @Override
    public List<Category> findByNameAndEnabled(String name, boolean enabled) {
        return categoryRepo.findByNameAndEnabled(name, enabled);
    }

    //@Transactional -> En caso se tenga varios updates, select, etc . en un solo metodo y Falla alguno se hace rollback
    @Override
    public List<Category> findByNameAndDescription(String name, String description) {
        return categoryRepo.findByNameAndDescription(name, description);
    }


    /*
    @Override
    public Category save(Category category) throws Exception {
        return repo.save(category);
    }

    @Override
    public Category update(Category category, Integer id) throws Exception {
        category.setIdCategory(id);
        return repo.save(category);
    }

    @Override
    public List<Category> readAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Category readById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Category());
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        repo.deleteById(id);
    }


     */


    /*

    @Override
    public Category validAndSave(Category category) {
        if(category.getIdCategory() > 0){
            return repo.save(category);
        }else{
            return new Category();
        }
    }

    */
}
