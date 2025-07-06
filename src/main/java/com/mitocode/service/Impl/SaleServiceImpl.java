package com.mitocode.service.Impl;

import com.mitocode.model.Sale;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.ISaleRepo;
import com.mitocode.service.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl extends CRUDImpl<Sale, Integer> implements ISaleService {

    private final ISaleRepo productRepo;

    @Override
    protected IGenericRepo<Sale, Integer> getRepo() {
        return productRepo;
    }

}
