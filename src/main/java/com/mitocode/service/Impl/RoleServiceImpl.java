package com.mitocode.service.Impl;

import com.mitocode.model.Role;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IRoleRepo;
import com.mitocode.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends CRUDImpl<Role, Integer> implements IRoleService {

    private final IRoleRepo productRepo;

    @Override
    protected IGenericRepo<Role, Integer> getRepo() {
        return productRepo;
    }

}
