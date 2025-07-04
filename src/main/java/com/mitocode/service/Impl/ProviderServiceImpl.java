package com.mitocode.service.Impl;

import com.mitocode.model.Product;
import com.mitocode.model.Provider;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IProductRepo;
import com.mitocode.repo.IProviderRepo;
import com.mitocode.service.IProductService;
import com.mitocode.service.IProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl extends CRUDImpl<Provider, Integer> implements IProviderService {

    private final IProviderRepo providerRepo;

    @Override
    protected IGenericRepo<Provider, Integer> getRepo() {
        return providerRepo;
    }

}
