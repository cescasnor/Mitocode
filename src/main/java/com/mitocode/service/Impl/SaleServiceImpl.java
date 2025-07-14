package com.mitocode.service.Impl;

import com.mitocode.dto.IProcedureDTO;
import com.mitocode.dto.ProcedureDTO;
import com.mitocode.model.Sale;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.ISaleRepo;
import com.mitocode.service.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl extends CRUDImpl<Sale, Integer> implements ISaleService {

    private final ISaleRepo productRepo;

    @Override
    protected IGenericRepo<Sale, Integer> getRepo() {
        return productRepo;
    }


    @Override
    public List<ProcedureDTO> callProcedure1() {
        List<ProcedureDTO> list = new ArrayList<>();
        productRepo.callProcedure1().forEach((e -> {
            ProcedureDTO dto = new ProcedureDTO();
            dto.setQuantityfn((Integer) e[1]);
            dto.setDatetimefn((String) e[0]);
            list.add(dto);
        }));
        return list;
    }

    @Override
    public List<IProcedureDTO> callProcedure2() {
        return callProcedure2();
    }
}
