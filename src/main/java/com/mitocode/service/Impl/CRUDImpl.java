package com.mitocode.service.Impl;

import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.ICRUD;

import java.lang.reflect.Method;
import java.util.List;

public abstract class CRUDImpl<T,ID> implements ICRUD<T,ID> {

    protected abstract IGenericRepo<T,ID> getRepo();

    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(T t, ID id) throws Exception {

        Class<?> clazz = t.getClass();
        String className = t.getClass().getSimpleName();
        String methodName = "setId" + className;
        Method setIdMethod = clazz.getMethod(methodName, id.getClass());
        setIdMethod.invoke(t,id);

        getRepo().findById(id).orElseThrow( () -> new ModelNotFoundException( "ID NOT FOUND " + id ));
        return getRepo().save(t);
    }

    @Override
    public List<T> readAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T readById(ID id) throws Exception {
        return getRepo().findById(id).orElseThrow( () -> new ModelNotFoundException("ID NOT FOUND " +  id));
    }

    @Override
    public void deleteById(ID id) throws Exception {
        getRepo().findById(id).orElseThrow( () -> new ModelNotFoundException( "ID NOT FOUND " + id ));
        getRepo().deleteById(id);
    }
}
